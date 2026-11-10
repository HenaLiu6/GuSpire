package gugumod.cards.food;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class FriedStinkyTofu extends BaseCard {
    public static final String ID = makeID("FriedStinkyTofu");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    public FriedStinkyTofu() {
        super(ID, info);

        setEthereal(true);
        setExhaust(true);
        setMagic(1, 1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber)));
    }
}
