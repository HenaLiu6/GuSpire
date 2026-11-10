package gugumod.cards.food;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.MaxHP;
import gugumod.util.CardStats;

public class Strawberry extends BaseCard {
    public static final String ID = makeID("Strawberry");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    public Strawberry() {
        super(ID, info);

        setMagic(2, 2);
        setEthereal(true);
        setExhaust(true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MaxHP(p, magicNumber)));
    }
}
