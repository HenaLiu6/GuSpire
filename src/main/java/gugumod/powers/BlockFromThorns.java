package gugumod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gugumod.GuguMod;
import org.apache.logging.log4j.Logger;

import static gugumod.GuguMod.makeID;

public class BlockFromThorns extends BasePower {
    public static final String POWER_ID = makeID("BlockFromThorns");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BlockFromThorns(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target == this.owner && power.ID.equals("Thorns")) {
            flash();
            addToBot(new GainBlockAction(owner, amount * power.amount));
        }

        super.onApplyPower(power, target, source);
    }
}
