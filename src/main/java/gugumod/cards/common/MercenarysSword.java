package gugumod.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;
import gugumod.util.OnKillCondition;
import gugumod.util.ReturnCardAction;
import gugumod.util.TriggerAction;

import java.util.Arrays;
import java.util.List;

public class MercenarysSword extends BaseCard {
    public static final String ID = makeID("MercenarysSword");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 8;
    private static final int DAMAGE_UPG = 4;
    private static final int COST_UPG = 0;

    List<AbstractGameAction> onKillActions = Arrays.asList(
            new ReturnCardAction(this),
            new GainEnergyAction(1)
    );

    public MercenarysSword() {
        super(ID, info);

        setDamage(DAMAGE, DAMAGE_UPG);
        setCostUpgrade(COST_UPG);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new TriggerAction(new OnKillCondition(), this, m, onKillActions));
    }
}
