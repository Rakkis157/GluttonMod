package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class ShareWeakness extends AbstractGluttonCard
{
    public static final String ID = "ShareWeakness";
    public static final String NAME = "Share Weakness";
    public static final String DESCRIPTION = "If you are Weak, apply !M! Weak to ALL enemies. NL Repeat for Frail and Vulnerable.";
    public static final String IMG_PATH = "cards/shareweakness.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public ShareWeakness()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
        {
            if(p.hasPower("Weakened")){
                flash();
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if ((!monster.isDead) && (!monster.isDying))
                    {
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(monster, p,
                                        new WeakPower(monster, this.magicNumber, false), this.magicNumber));
                    }
                }
            }
            if(p.hasPower("Frail")){
                flash();
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if ((!monster.isDead) && (!monster.isDying))
                    {
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(monster, p,
                                        new FrailPower(monster, this.magicNumber, false), this.magicNumber));
                    }
                }
            }
            if(p.hasPower("Vulnerable")){
                flash();
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if ((!monster.isDead) && (!monster.isDying))
                    {
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(monster, p,
                                        new VulnerablePower(monster, this.magicNumber, false), this.magicNumber));
                    }
                }
            }
        }
    }

    public AbstractCard makeCopy()
    {
        return new ShareWeakness();
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


