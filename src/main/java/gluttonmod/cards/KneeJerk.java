package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KneeJerk extends AbstractGluttonCard
{
    public static final int HP_LOSS_CHECK = 3;

    public static final String ID = "KneeJerk";
    public static final String NAME = "Knee-Jerk";
    public static final String DESCRIPTION = "Deal !D! damage. If you have lost at least "
        + HP_LOSS_CHECK + " HP this turn, Gain [R] [R] .";
    public static final String IMG_PATH = "cards/kneejerk.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int POWER = 10;
    private static final int UPGRADE_BONUS = 4;
    private static final int ENERGY_GAIN = 2;

    public KneeJerk()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
        if(GameActionManager.damageReceivedThisTurn >= HP_LOSS_CHECK){
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(ENERGY_GAIN));
        }
    }

    public AbstractCard makeCopy()
    {
        return new KneeJerk();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
