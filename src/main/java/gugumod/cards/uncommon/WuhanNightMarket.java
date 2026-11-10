package gugumod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.WuhanPower;
import gugumod.util.CardStats;

public class WuhanNightMarket extends BaseCard {
    public static final String ID = makeID("WuhanNightMarket");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public WuhanNightMarket() {
        super(ID, info);

        setMagic(1, 1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WuhanPower(p, magicNumber), magicNumber));
    }
}
