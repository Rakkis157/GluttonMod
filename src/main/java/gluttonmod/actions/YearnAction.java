package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YearnAction extends AbstractGameAction{

    private int echoAmount;

    public YearnAction(int echoAmount){
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.echoAmount = echoAmount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.isEmpty())
        {
            this.isDone = true;
            return;
        }
        AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
        if(card.type != AbstractCard.CardType.STATUS
            && card.type != AbstractCard.CardType.CURSE){
            AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(card, this.echoAmount));
        }
        this.isDone = true;
    }
}
