package gluttonmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import gluttonmod.actions.DistilledAgonyAction;

public class DistilledAgony extends AbstractGluttonCard
{
    public static final String ID = "DistilledAgony";
    public static final String NAME = "Distilled Agony";
    public static final String DESCRIPTION = "Lose !M! HP and deal !D! damage X times.";
    public static final String IMG_PATH = "cards/distilledagony.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = -1;
    private static final int MAGIC = 2;
    private static final int POWER = 10;
    private static final int UPGRADE_BONUS = 3;

    public DistilledAgony()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.baseDamage = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        AbstractDungeon.actionManager.addToBottom(
                new DistilledAgonyAction(p, m, this.damage, this.magicNumber,
                        this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy()
    {
        return new DistilledAgony();
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
