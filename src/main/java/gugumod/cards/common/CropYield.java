package gugumod.cards.common;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class CropYield extends BaseCard {
    public static final String ID = makeID("CropYield");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            0
    );

    public CropYield() {
        super(ID, info);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard food = gugumod.util.GetRandomCard.getRandomFoodCard();
        if (upgraded) {
            food.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(food));

        AbstractCard food2 = gugumod.util.GetRandomCard.getRandomFoodCard();
        if (upgraded) {
            food2.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(food2));
    }
}
