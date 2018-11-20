package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gluttonmod.powers.BleedPower;

public class Hemophilia extends AbstractGluttonCard
{
    private static int PAIN = 1;
    public static final String ID = "Hemophilia";
    public static final String NAME = "Hemophilia";
    public static final String DESCRIPTION = "Gain " + PAIN + " Bleed. Apply !M! Bleed to ALL enemies.";
    public static final String IMG_PATH = "cards/hemophilia.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL;

    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public Hemophilia()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
        {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p,
                            new BleedPower(p, p, PAIN), PAIN));
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying))
                {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(monster, p,
                                    new BleedPower(monster, p, this.magicNumber), this.magicNumber));
                }
            }
        }
    }

    public AbstractCard makeCopy()
    {
        return new Hemophilia();
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


