package gugumod.relics;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gugumod.character.GuguCharacter;

import static gugumod.GuguMod.makeID;

public class GugusHealth extends BaseRelic {
    private static final String NAME = "GugusHealth"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public GugusHealth() {
        super(ID, NAME, GuguCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    private static final int HEALTH = 40;

    @Override
    public void atPreBattle() {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        int healthDiff = p.currentHealth - HEALTH;
        if (healthDiff > 0) {
            addToBot(new LoseHPAction(p, p, healthDiff));
            addToBot(new GainGoldAction(healthDiff));
        } else {
            addToBot(new HealAction(p, p, -healthDiff));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEALTH + DESCRIPTIONS[1];
    }
}