package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DelusionAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static String[] TEXT = {"Choose a card to echo. You cannot echo ethereal cards."};

    public DelusionAction(AbstractPlayer p, int amount){
        this.p = p;
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if(this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() == 1) {
                AbstractCard c = this.p.hand.getTopCard();
                if (!c.isEthereal) {
                    AbstractDungeon.actionManager.addToBottom(
                            new MakeEchoAction(c, this.amount)
                    );
                }
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (!c.isEthereal) {
                    AbstractDungeon.actionManager.addToBottom(
                            new MakeEchoAction(c, this.amount)
                    );
                }
                p.hand.addToTop(c);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
