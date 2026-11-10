package gugumod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gugumod.character.GuguCharacter;

import static gugumod.GuguMod.makeID;

public class ThornyRose extends BaseRelic {
    private static final String NAME = "ThornyRose";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public ThornyRose() {
        super(ID, NAME, GuguCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onLoseHp(int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new DamageAllEnemiesAction(p,
                p.getPower("Thorns").amount,
                DamageInfo.DamageType.THORNS,
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        super.onLoseHp(damageAmount);
    }
}
