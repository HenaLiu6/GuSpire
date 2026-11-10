package gugumod.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OnKillCondition implements TriggerCondition {
    @Override
    public boolean shouldTrigger(AbstractCard card, AbstractMonster target) {
        return (target.isDying || target.currentHealth <= 0)
                && !target.halfDead;
    }
}
