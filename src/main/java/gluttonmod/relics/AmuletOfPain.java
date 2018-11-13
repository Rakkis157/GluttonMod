package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class AmuletOfPain extends AbstractGluttonRelic {
    public static final String ID = "AmuletOfPain";
    private static final RelicTier TIER = RelicTier.COMMON;
    private static final String IMG = "relics/amuletofpain.png";
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int COUNT = 5;

    public AmuletOfPain() {
        super(ID, IMG, TIER, SOUND);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onLoseHp(int damageAmount){
        if(damageAmount >0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.counter += 1;
            if (this.counter == COUNT)
            {
                this.counter = 0;
                flash();
                this.pulse = false;
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                        DamageInfo.createDamageMatrix(5, true),
                        DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
            else if (this.counter == COUNT-1)
            {
                beginPulse();
                this.pulse = true;
            }
        }
    }


    public void atBattleStart()
    {
        if (this.counter == COUNT-1)
        {
            beginPulse();
            this.pulse = true;
        }
    }

    public AbstractRelic makeCopy()
    {
        return new AmuletOfPain();
    }
}
