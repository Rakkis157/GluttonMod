package gluttonmod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Lollipop extends AbstractGluttonRelic {
    public static final String ID = "Lollipop";
    private static final RelicTier TIER = RelicTier.SHOP;
    private static final String IMG = "relics/lollipop.png";
    private static final LandingSound SOUND = LandingSound.CLINK;

    public Lollipop() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Lollipop();
    }
}
