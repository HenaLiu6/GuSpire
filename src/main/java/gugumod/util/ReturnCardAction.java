package gugumod.util;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReturnCardAction extends AbstractGameAction {
    // ~Equivalent to MakeTempCardInHandAction
    private final AbstractCard card;

    public ReturnCardAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (p.hand.size() < BaseMod.MAX_HAND_SIZE) {
            AbstractCard copy = card.makeStatEquivalentCopy();
            p.hand.addToHand(copy);
            copy.triggerWhenDrawn();
            copy.flash();
        }

        isDone = true;
    }
}
