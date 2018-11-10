package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fast extends AbstractGluttonCard {
    public static final String ID = "Fast";
    public static final String NAME = "Fast";
    public static final String DESCRIPTION = "Draw !M! Cards. Shuffle two Hunger_Pangs into your discard pile.";
    public static final String IMG_PATH = "cards/fast.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int MAGIC = 3;
    private static final int UPGRADE_BONUS = 2;
    private static final int HUNGER = 2;

    public Fast()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new HungerPang(),
                HUNGER));
    }

    public AbstractCard makeCopy()
    {
        return new Fast();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}