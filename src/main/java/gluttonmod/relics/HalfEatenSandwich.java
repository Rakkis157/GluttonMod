package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class HalfEatenSandwich extends AbstractGluttonRelic {
    public static final String ID = "HalfEatenSandwich";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final String IMG = "relics/sandwich.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public HalfEatenSandwich() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onVictory()
    {
        flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(2);
        }
        AbstractDungeon.player.increaseMaxHp(1, true);
    }

    public AbstractRelic makeCopy() {
        return new HalfEatenSandwich();
    }
}
