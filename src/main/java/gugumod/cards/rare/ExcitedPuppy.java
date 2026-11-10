package gugumod.cards.rare;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.MakiIns;
import gugumod.util.CardStats;


public class ExcitedPuppy extends BaseCard {
    public static final String ID = makeID("ExcitedPuppy");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public ExcitedPuppy() {
        super(ID, info);
        setCostUpgrade(0);
        setEthereal(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 3));
        addToBot(new ApplyPowerAction(p, p, new MakiIns(p, 0)));
    }
}