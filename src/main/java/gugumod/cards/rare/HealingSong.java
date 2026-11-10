package gugumod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.AmanoIns;
import gugumod.powers.YuzusSong;
import gugumod.util.CardStats;

public class HealingSong extends BaseCard {
    public static final String ID = makeID("HealingSong");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public HealingSong() {
        super(ID, info);

        setMagic(1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new YuzusSong(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new AmanoIns(p, 0)));
    }
}
