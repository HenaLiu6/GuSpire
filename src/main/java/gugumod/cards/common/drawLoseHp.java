package gugumod.cards.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class drawLoseHp extends BaseCard {
    public static final String ID = makeID("drawLoseHp");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public drawLoseHp() {
        super(ID, info);
        setMagic(2, 2);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, magicNumber));
        addToBot(new DrawCardAction(p, 1));
    }
}