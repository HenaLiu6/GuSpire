package gugumod.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface TriggerCondition {
    boolean shouldTrigger(AbstractCard card, AbstractMonster target);
}
