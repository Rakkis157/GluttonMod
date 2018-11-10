package gluttonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import gluttonmod.cards.AbstractGluttonCard;

@SpirePatch(clz = AbstractPlayer.class, method = "gainGold")
public class GainGoldPatch {

    public static void Postfix(AbstractPlayer abstractPlayer, int amount) {
        if(amount > 0) {
            applyToCardsInGroup(abstractPlayer.drawPile, amount);
            applyToCardsInGroup(abstractPlayer.discardPile, amount);
            applyToCardsInGroup(abstractPlayer.hand, amount);
            applyToCardsInGroup(abstractPlayer.exhaustPile, amount);
        }
    }

    private static void applyToCardsInGroup(CardGroup cardGroup, int amount){
        for (AbstractCard card : cardGroup.group) {
            if (card instanceof AbstractGluttonCard) {
                AbstractGluttonCard goldenCard = (AbstractGluttonCard) card;
                goldenCard.onChangeGold(amount);
            }
        }
    }
}
