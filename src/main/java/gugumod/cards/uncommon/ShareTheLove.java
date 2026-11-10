package gugumod.cards.uncommon;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.SelfDmgToRnd;
import gugumod.util.CardStats;

public class ShareTheLove  extends BaseCard {
    public static final String ID = makeID("ShareTheLove");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public ShareTheLove() {
        super(ID, info);

        setMagic(0, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SelfDmgToRnd(p, 1)));
        addToBot(new DrawCardAction(p, magicNumber));
    }
}
