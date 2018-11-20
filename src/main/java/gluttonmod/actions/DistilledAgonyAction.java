package gluttonmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class DistilledAgonyAction
        extends AbstractGameAction
{
    private boolean freeToPlayOnce = false;
    private int damage;
    private int pain;
    private AbstractPlayer p;
    private AbstractMonster m;
    private DamageInfo.DamageType damageTypeForTurn;
    private int energyOnUse = -1;

    public DistilledAgonyAction(AbstractPlayer p, AbstractMonster m, int damage, int pain, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse)
    {
        this.p = p;
        this.m = m;
        this.damage = damage;
        this.pain = pain;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
    }

    public void update()
    {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (this.p.hasRelic("Chemical X"))
        {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (effect > 0)
        {
            for (int i = 0; i < effect; i++) {
                AbstractDungeon.actionManager.addToBottom(new ConditionalLoseHPAction(p, p, this.pain, this.m));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
