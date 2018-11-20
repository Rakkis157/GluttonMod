package gluttonmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Syringe extends AbstractGluttonRelic {
    public static final String ID = "Syringe";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final String IMG = "relics/syringe.png";
    private static final LandingSound SOUND = LandingSound.CLINK;

    public Syringe() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override

    public int onPlayerHeal(int healAmount)
    {
        if(healAmount >0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(
                    new DamageRandomEnemyAction(
                            new DamageInfo(AbstractDungeon.player, 6, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.POISON));
        }
        return healAmount;
    }

    public AbstractRelic makeCopy() {
        return new Syringe();
    }
}
