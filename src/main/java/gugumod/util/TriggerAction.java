package gugumod.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class TriggerAction extends AbstractGameAction {
    private final TriggerCondition condition;
    private final List<AbstractGameAction> results;
    private final AbstractCard card;
    private final AbstractMonster target;

    public TriggerAction(TriggerCondition condition, AbstractCard card, AbstractMonster target, List<AbstractGameAction> results) {
        this.condition = condition;
        this.card = card;
        this.target = target;
        this.results = results;
    }

    @Override
    public void update() {
        if (condition.shouldTrigger(card, target)) {
            for (AbstractGameAction action : results) {
                AbstractDungeon.actionManager.addToTop(action);
            }
        }
        isDone = true;
    }
}
