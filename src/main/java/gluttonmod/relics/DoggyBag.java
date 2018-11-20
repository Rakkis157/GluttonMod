package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class DoggyBag extends AbstractGluttonRelic {
    public static final String ID = "DoggyBag";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final String IMG = "relics/doggybag.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public DoggyBag() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onEnterRoom(AbstractRoom room)
    {
        if ((room instanceof MonsterRoomElite))
        {
            this.pulse = true;
            beginPulse();
        }
        else
        {
            this.pulse = false;
        }
    }

    public void onVictory()
    {
        if ((AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite))
        {
            flash();
            this.pulse = false;
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.player.increaseMaxHp(10, true);
        }
    }

    public AbstractRelic makeCopy() {
        return new DoggyBag();
    }
}
