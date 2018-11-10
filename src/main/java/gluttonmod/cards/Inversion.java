package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Inversion extends AbstractGluttonCard
{
    public static final String ID = "Inversion";
    public static final String NAME = "Inversion";
    public static final String DESCRIPTION = "If you are Weak, gain !M! Strength. NL If you are Frail, gain !M! Dexterity. NL If you are Vulnerable, gain !B! Block.";
    public static final String IMG_PATH = "cards/inversion.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC_BONUS = 1;
    private static final int BLOCK = 15;
    private static final int UPGRADE_BLOCK_BONUS = 5;

    public Inversion()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(p.hasPower("Weakened")){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        }
        if(p.hasPower("Frail")){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        }
        if(p.hasPower("Vulnerable")){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Inversion();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_BONUS);
            upgradeBlock(UPGRADE_BLOCK_BONUS);
        }
    }
}


