package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Demolish extends AbstractGluttonCard {
    public static final String ID = "Demolish";
    public static final String NAME = "Demolish";
    public static final String DESCRIPTION = "Deal !D! damage to ALL enemies. NL If this card is Exhausted, deal !D! damage to ALL enemies three times.";
    public static final String IMG_PATH = "cards/demolish.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 1;
    private static final int POWER = 4;
    private static final int UPGRADE_BONUS = 2;

    public Demolish()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseDamage = POWER;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void triggerOnExhaust()
    {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public AbstractCard makeCopy()
    {
        return new Demolish();
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
