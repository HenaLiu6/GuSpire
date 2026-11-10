package gugumod.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.FlashOfSteel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gugumod.cards.food.FriedStinkyTofu;
import gugumod.cards.food.Strawberry;
import gugumod.cards.food.HotDryNoodles;

public class GetRandomCard {
    private static final Class<? extends AbstractCard>[] FOOD_CARDS = new Class[] {
            FriedStinkyTofu.class,
            HotDryNoodles.class,
            Strawberry.class
    };

    public static AbstractCard getRandomFoodCard() {
        int index = AbstractDungeon.cardRandomRng.random(FOOD_CARDS.length - 1);
        try {
            return FOOD_CARDS[index].getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // Fallback or logging
            e.printStackTrace();
            return new FlashOfSteel(); // fallback card
        }
    }
}
