package com.megacrit.cardcrawl.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import gluttonmod.cards.AbstractGluttonCard;

public class SpendGoldCombatAction
        extends AbstractGameAction
{
    private int loseGold;
    private static final float DURATION = 0.1F;

    public SpendGoldCombatAction(int goldAmount)
    {
        this.loseGold = goldAmount;
        this.actionType = ActionType.DEBUFF;
        this.duration = DURATION;
    }

    public void update()
    {
        if (this.duration == 0.1F)
        {
            AbstractPlayer p = AbstractDungeon.player;
            p.loseGold(this.loseGold);
            for (int i = 0; i < this.loseGold; i++) {
                AbstractDungeon.effectList.add(
                        new GainPennyEffect(p, p.hb.cX, p.hb.cY, 0, 0, false));
            }
        }
        tickDuration();

        for(AbstractCard c : AbstractDungeon.player.hand.group){
            if(c instanceof AbstractGluttonCard){
                ((AbstractGluttonCard)c).onLoseGoldFromCard(this.loseGold);
            }
        }
        for(AbstractCard c : AbstractDungeon.player.drawPile.group){
            if(c instanceof AbstractGluttonCard){
                ((AbstractGluttonCard)c).onLoseGoldFromCard(this.loseGold);
            }
        }
        for(AbstractCard c : AbstractDungeon.player.discardPile.group){
            if(c instanceof AbstractGluttonCard){
                ((AbstractGluttonCard)c).onLoseGoldFromCard(this.loseGold);
            }
        }
        for(AbstractCard c : AbstractDungeon.player.exhaustPile.group){
            if(c instanceof AbstractGluttonCard){
                ((AbstractGluttonCard)c).onLoseGoldFromCard(this.loseGold);
            }
        }
    }
}
