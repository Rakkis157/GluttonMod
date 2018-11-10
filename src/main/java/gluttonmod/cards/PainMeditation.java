package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PainMeditation extends AbstractGluttonCard
{
    public static final int HP_LOSS_CHECK = 3;

    public static final String ID = "PainMeditation";
    public static final String NAME = "Pain Meditation";
    public static final String DESCRIPTION = "Draw a card. If you have lost at least "
            + HP_LOSS_CHECK + " HP this turn, draw another card.";
    public static final String IMG_PATH = "cards/painmeditation.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public PainMeditation()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
        if(GameActionManager.damageReceivedThisTurn >= HP_LOSS_CHECK){
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    public AbstractCard makeCopy()
    {
        return new PainMeditation();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}

