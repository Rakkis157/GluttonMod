package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class Retaliation extends AbstractGluttonCard {
    public static final String ID = "Retaliation";
    public static final String NAME = "Retaliation";
    public static final String DESCRIPTION = "Deal damage equal to twice the number of times you have lost HP this combat. NL Exhaust.";
    public static final String EXTENDED_DESCRIPTION = " NL (Deals !D! damage.)";
    public static final String UPGRADE_DESCRIPTION = "Deal damage equal to thrice the number of times you have lost HP this combat. NL Exhaust.";
    public static final String IMG_PATH = "cards/retaliation.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 2;
    private static final int MAGIC = 2;
    private static final int POWER = 0;
    private static final int UPGRADE_BONUS = 1;

    public Retaliation()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        if(!this.upgraded) {
            this.rawDescription = DESCRIPTION;
        }
        else{
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        initializeDescription();
    }

    public AbstractCard makeCopy()
    {
        return new Retaliation();
    }

    public void applyPowers()
    {
        this.baseDamage = AbstractDungeon.player.damagedThisCombat * this.magicNumber;
        super.applyPowers();
        if(!this.upgraded) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION);
        }
        else {
            this.rawDescription = (UPGRADE_DESCRIPTION + EXTENDED_DESCRIPTION);
        }
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        if(!this.upgraded) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION);
        }
        else {
            this.rawDescription = (UPGRADE_DESCRIPTION + EXTENDED_DESCRIPTION);
        }
        initializeDescription();
    }

    public void upgrade()
    {
        if (!this.upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
