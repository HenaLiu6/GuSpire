package gugumod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class Defend extends BaseCard {
    public static final String ID = makeID("Defend");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    private static final int DEFENSE = 7;
    private static final int RESTORE = 2;
    private static final int UPG_DEFENSE = 4;
    private static final int UPG_RESTORE = 1;

    public Defend() {
        super(ID, info);

        setBlock(DEFENSE, UPG_DEFENSE);
        setMagic(RESTORE, UPG_RESTORE);

        tags.add(CardTags.STARTER_DEFEND);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, magicNumber));
        addToBot(new GainBlockAction(p, block));
    }
}