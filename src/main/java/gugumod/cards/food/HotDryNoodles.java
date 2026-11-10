package gugumod.cards.food;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class HotDryNoodles extends BaseCard {
    public static final String ID = makeID("HotDryNoodles");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ALL,
            0
    );

    public HotDryNoodles() {
        super(ID, info);

        setMagic(2, 2);
        setEthereal(true);
        setExhaust(true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, magicNumber));
        addToBot(new DamageAllEnemiesAction(p, magicNumber, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE));
    }
}
