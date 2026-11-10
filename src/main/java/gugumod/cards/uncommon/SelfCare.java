package gugumod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class SelfCare extends BaseCard {
    public static final String ID = makeID("SelfCare");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public SelfCare() {
        super(ID, info);

        setMagic(8, 10);
        setCustomVar("heal",8, 12);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, customVar("heal")));
        addToBot(new LoseHPAction(p, p, magicNumber));
    }
}
