package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BellySlam extends AbstractGluttonCard {
    public static final String ID = "BellySlam";
    public static final String NAME = "Belly Slam";
    public static final String DESCRIPTION = "Deal damage equal to your HP divided by !M!.";
    public static final String EXTENDED_DESCRIPTION = " NL (Deals !D! damage.)";
    public static final String IMG_PATH = "cards/bellyslam.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int MAGIC = 4;
    private static final int POWER = 0;
    private static final int UPGRADE_BONUS = -1;

    public BellySlam()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));

        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public AbstractCard makeCopy()
    {
        return new BellySlam();
    }

    public void applyPowers()
    {
        this.baseDamage = AbstractDungeon.player.currentHealth / this.magicNumber;
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
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
