package gugumod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.BlockFromThorns;
import gugumod.util.CardStats;

public class thorns_block extends BaseCard {
    public static final String ID = makeID("thorns_block");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public thorns_block() {
        super(ID, info);

        setBlock(5);
        setCostUpgrade(0);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));

        addToBot(new ApplyPowerAction(p, p, new BlockFromThorns(p, 1)));
    }
}
