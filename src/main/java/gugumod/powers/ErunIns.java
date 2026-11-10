package gugumod.powers;

import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gugumod.util.Tracker27;

import static gugumod.GuguMod.characterPath;
import static gugumod.GuguMod.makeID;


public class ErunIns extends BasePower {
    public static final String POWER_ID = makeID("ErunIns");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final SpriterAnimation animation = new SpriterAnimation(characterPath("animation/Erun/gugu.scml"));


    public ErunIns(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount); //Try -1 for amount
    }

    @Override
    public void onInitialApplication() {
        Tracker27.checkAndTrigger27(AbstractDungeon.player);
    }

    public void renderAnimation(SpriteBatch sb) {
        if (animation != null) {
            animation.renderSprite(sb,
                    Settings.WIDTH * 0.44f, // X position: center screen
                    Settings.HEIGHT * 0.55f   // Y position: just above player
            );
        }
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        renderAnimation(sb);
    }
}