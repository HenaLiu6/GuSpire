package gugumod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class thornsDamage extends BaseCard {
    public static final String ID = makeID("thornsDamage");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2
    );

    public thornsDamage() {
        super(ID, info);
        upgradeBaseCost(1);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int thorns = 0;
        if (p.hasPower("Thorns")) {
            thorns = p.getPower("Thorns").amount;
        }

        addToBot(new DamageRandomEnemyAction(new DamageInfo(p, thorns, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
