package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Gemstone extends AbstractGluttonRelic {
    public static final String ID = "Gemstone";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final String IMG = "relics/gemstone.png";
    private static final LandingSound SOUND = LandingSound.CLINK;

    public Gemstone() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart()
    {
        if(AbstractDungeon.player.gold >= 300) {
            flash();
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new StrengthPower(AbstractDungeon.player, 2), 2));
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new DexterityPower(AbstractDungeon.player, 2), 2));

            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public AbstractRelic makeCopy() {
        return new Gemstone();
    }
}
