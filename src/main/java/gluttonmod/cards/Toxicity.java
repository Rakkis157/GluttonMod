package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class Toxicity extends AbstractGluttonCard
{
    private static final int DRAWBACK = 2;
    public static final String ID = "Toxicity";
    public static final String NAME = "Toxicity";
    public static final String DESCRIPTION = "Gain " + DRAWBACK + " Weak, Frail, and Vulnerable. NL Apply !M! Poison.";
    public static final String IMG_PATH = "cards/toxicity.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;
    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC_BONUS = 3;

    public Toxicity()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new WeakPower(p, DRAWBACK, false), DRAWBACK));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new FrailPower(p, DRAWBACK, false), DRAWBACK));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new VulnerablePower(p, DRAWBACK, false), DRAWBACK));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber),
                        this.magicNumber, AbstractGameAction.AttackEffect.POISON));
    }

    public AbstractCard makeCopy()
    {
        return new Toxicity();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
        }
    }
}



