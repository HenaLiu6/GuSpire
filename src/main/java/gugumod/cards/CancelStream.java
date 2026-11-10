package gugumod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class CancelStream extends BaseCard {
    //Save energy for next turn, maybe +1 energy and +some defense
    public static final String ID = makeID("CancelStream");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    public CancelStream() {
        super(ID, info);

        setBlock(5, 2);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(1));
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, block), block));
    }
}
