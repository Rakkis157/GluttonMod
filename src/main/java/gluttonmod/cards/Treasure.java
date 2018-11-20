package gluttonmod.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class Treasure extends AbstractGluttonCard
{
    private static final int GOLD = 50;
    private static final int EXCHANGE = 100;

    public static final String ID = "Treasure";
    public static final String NAME = "Treasure";
    public static final String DESCRIPTION = "Gain " + GOLD + " Gold. NL Heal !M! HP for every "
            + EXCHANGE + " Gold you have. NL Exhaust.";
    public static final String IMG_PATH = "cards/treasure.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC_BONUS = 1;

    public Treasure()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        p.gainGold(GOLD);
        for (int i = 0; i < GOLD; i++) {
            AbstractDungeon.effectList.add(new GainPennyEffect(p, 0, 0, p.hb.cX, p.hb.cY, true));
        }
        int heal = p.gold / EXCHANGE * this.magicNumber;
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, heal));
    }

    public AbstractCard makeCopy()
    {
        return new Treasure();
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

