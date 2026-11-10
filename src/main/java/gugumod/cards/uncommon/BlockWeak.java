package gugumod.cards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class BlockWeak extends BaseCard {
    public static final String ID = makeID("BlockWeak");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public BlockWeak() {
        super(ID, info);

        setBlock(15, 5);
        setMagic(2, 1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (m2) -> new WeakPower(m2, magicNumber, false)));
    }
}