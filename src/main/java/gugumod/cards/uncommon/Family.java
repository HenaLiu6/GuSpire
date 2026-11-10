package gugumod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.MaxHP;
import gugumod.util.CardStats;

public class Family extends BaseCard {
    public static final String ID = makeID("Family");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public Family() {
        super(ID, info);

        setMagic(5, 8);
        setBlock(2, 4);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MaxHP(p, magicNumber), magicNumber));
        addToBot(new GainBlockAction(p, block));
    }
}
