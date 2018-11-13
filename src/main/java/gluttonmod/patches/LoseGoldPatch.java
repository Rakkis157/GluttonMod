package gluttonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import gluttonmod.GluttonMod;
import gluttonmod.cards.AbstractGluttonCard;

import java.util.Stack;

@SpirePatch(clz = AbstractPlayer.class, method = "loseGold")
public class LoseGoldPatch {

    // I have no reason to believe loseGold will ever be called from within itself, but I want to be safe
    private static Stack<Integer> goldStack = new Stack<>();

    public static void Prefix(AbstractPlayer abstractPlayer, int amount) {
        if(amount > abstractPlayer.gold) {
            goldStack.push(abstractPlayer.gold);
        }
        else {
            goldStack.push(amount);
        }
    }

    public static void Postfix(AbstractPlayer abstractPlayer, int amount) {
        int realAmount = goldStack.pop();
        if(realAmount > 0) {
            applyToCardsInGroup(abstractPlayer.drawPile, realAmount);
            applyToCardsInGroup(abstractPlayer.discardPile, realAmount);
            applyToCardsInGroup(abstractPlayer.hand, realAmount);
            applyToCardsInGroup(abstractPlayer.exhaustPile, realAmount);
        }
    }

    private static void applyToCardsInGroup(CardGroup cardGroup, int amount){
        for (AbstractCard card : cardGroup.group) {
            if (card instanceof AbstractGluttonCard) {
                AbstractGluttonCard goldenCard = (AbstractGluttonCard) card;
                goldenCard.onChangeGold(-1 * amount);
            }
        }
    }
}
