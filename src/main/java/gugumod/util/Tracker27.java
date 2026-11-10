package gugumod.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gugumod.powers.AmanoIns;
import gugumod.powers.ErunIns;
import gugumod.powers.MakiIns;
import gugumod.powers.RaidenIns;

import java.util.Arrays;

public class Tracker27 {
    private static final String[] REQUIRED_POWER_IDS = {
            RaidenIns.POWER_ID,
            MakiIns.POWER_ID,
            AmanoIns.POWER_ID,
            ErunIns.POWER_ID
    };

    public static void checkAndTrigger27(AbstractPlayer player) {
        boolean hasAll = Arrays.stream(REQUIRED_POWER_IDS)
                .allMatch(player::hasPower);

        if (hasAll) {
            // Remove the powers
            Arrays.stream(REQUIRED_POWER_IDS).forEach(id ->
                    AbstractDungeon.actionManager.addToBottom(
                            new RemoveSpecificPowerAction(player, player, id)
                    )
            );

            // Deal 27 to all enemies
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(
                            player,
                            DamageInfo.createDamageMatrix(27, true),
                            DamageInfo.DamageType.THORNS,
                            AbstractGameAction.AttackEffect.FIRE
                    )
            );

            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(player, player, 27)
            );
        }
    }
}
