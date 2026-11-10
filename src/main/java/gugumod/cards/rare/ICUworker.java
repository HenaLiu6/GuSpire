package gugumod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.RaidenIns;
import gugumod.util.CardStats;

public class ICUworker extends BaseCard {
    public static final String ID = makeID("ICUworker");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    public ICUworker() {
        super(ID, info);
        setMagic(15, 10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new RaidenIns(p, 0)));
    }
}