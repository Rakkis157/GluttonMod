package gluttonmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gluttonmod.GluttonMod;

public class FeverVisionsPower extends AbstractGluttonPower {
    public static final String POWER_ID = "FeverVisions";
    public static final String NAME = "Fever Visions";
    public static final String[] DESCRIPTIONS = {"At the start of your turn, if you are Frail, Vulnerable, or Weak, draw",
            "card.", "cards."};
    public static final String IMG = "powers/fevervisions.png";

    public FeverVisionsPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;

        this.img = new Texture(GluttonMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if(amount == 1) {
            description = DESCRIPTIONS[0] + " " + amount + " " + DESCRIPTIONS[1];
        }
        else{
            description = DESCRIPTIONS[0] + " " + amount + " " + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if(this.owner.hasPower("Frail")
                || this.owner.hasPower("Vulnerable")
                || this.owner.hasPower("Weakened")){
            flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
        }
    }
}
