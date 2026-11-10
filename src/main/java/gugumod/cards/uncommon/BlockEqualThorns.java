package gugumod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class BlockEqualThorns extends BaseCard {
    public static final String ID = makeID("BlockEqualThorns");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public BlockEqualThorns() {
        super(ID, info);

        setBlock(0, 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int thorns = 0;
        if (p.hasPower("Thorns")) {
            thorns = p.getPower("Thorns").amount;
        }
        addToBot(new GainBlockAction(p, thorns + block));
    }
}