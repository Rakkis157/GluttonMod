package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class Excrescence extends AbstractGluttonCard {
    public static final String ID = "Excrescence";
    public static final String NAME = "Excrescence";
    public static final String DESCRIPTION = "Deal damage equal to the number of cards in your deck. Shuffle !M! Hunger_Pangs into your discard pile.";
    public static final String EXTENDED_DESCRIPTION = " NL (Deals !D! damage.)";
    public static final String IMG_PATH = "cards/excrescence.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int MAGIC = 2;
    private static final int POWER = 0;
    private static final int UPGRADE_COST = 1;

    public Excrescence()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new HungerPang(),
                this.magicNumber));

        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public AbstractCard makeCopy()
    {
        return new Excrescence();
    }

    public void applyPowers()
    {
        this.baseDamage = AbstractDungeon.player.drawPile.size()
                + AbstractDungeon.player.discardPile.size()
                + AbstractDungeon.player.hand.size();
        super.applyPowers();
        this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION);
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION);
        initializeDescription();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
