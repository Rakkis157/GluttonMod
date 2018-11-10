package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Migraine extends AbstractGluttonCard
{
    public static final String ID = "Migraine";
    public static final String NAME = "Migraine";
    public static final String DESCRIPTION = "Unplayable. NL When drawn, lose 1 HP and draw 1 card.";
    public static final String UPGRADE_DESCRIPTION = "Unplayable. NL When drawn, lose 1 HP and draw 2 cards.";
    public static final String CANT_PLAY = "I can't play this card.";
    public static final String IMG_PATH = "cards/migraine.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    private static final int COST = -2;
    private static final int PAIN = 1;
    private static final int UPGRADE_CARD_DRAW = 2;
    private int cardDrawAmount = 1;

    public Migraine()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        this.cantUseMessage = CANT_PLAY;
        return false;
    }

    public void triggerWhenDrawn()
    {
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player,
                        PAIN, AbstractGameAction.AttackEffect.FIRE));

        if(!AbstractDungeon.player.hasPower("No Draw")){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.cardDrawAmount));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Migraine();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            this.cardDrawAmount = UPGRADE_CARD_DRAW;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


