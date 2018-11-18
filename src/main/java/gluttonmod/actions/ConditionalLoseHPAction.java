package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ConditionalLoseHPAction
        extends AbstractGameAction
{
    private static final float DURATION = 0.33F;

    private AbstractCreature check;

    public ConditionalLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AbstractCreature check)
    {
        this(target, source, amount, check, AbstractGameAction.AttackEffect.NONE);
    }

    public ConditionalLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AbstractCreature check, AbstractGameAction.AttackEffect effect)
    {
        setValues(target, source, amount);
        this.check = check;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.33F;
    }

    public void update()
    {
        if ((this.duration == 0.33F) && (this.target.currentHealth > 0) && (this.check.currentHealth > 0))
        {
            this.target.damageFlash = true;
            this.target.damageFlashFrames = 4;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        }
        tickDuration();
        if (this.isDone)
        {
            if(this.check.currentHealth > 0) {
                this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
        }
    }
}
