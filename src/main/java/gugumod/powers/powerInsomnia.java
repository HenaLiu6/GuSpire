package gugumod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static gugumod.GuguMod.makeID;

public class powerInsomnia extends BasePower {
    public static final String POWER_ID = makeID("powerInsomnia");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public powerInsomnia(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractCreature p = this.owner;
            List<AbstractPower> potentialPowers = Arrays.asList(
                    new RaidenIns(p, 0),
                    new MakiIns(p, 0),
                    new AmanoIns(p, 0),
                    new ErunIns(p, 0)
            );

            List<AbstractPower> missingPowers = potentialPowers.stream()
                    .filter(power -> !p.hasPower(power.ID))
                    .collect(Collectors.toList());

            Collections.shuffle(missingPowers, new java.util.Random(AbstractDungeon.cardRandomRng.randomLong()));

            // Apply up to X missing powers
            for (int i = 0; i < Math.min(amount, missingPowers.size()); i++) {
                AbstractPower powerToApply = missingPowers.get(i);
                addToBot(new ApplyPowerAction(p, p, powerToApply));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
