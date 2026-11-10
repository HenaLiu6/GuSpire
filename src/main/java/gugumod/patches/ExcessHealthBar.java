package gugumod.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gugumod.powers.MaxHP;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "renderHealth"
)
public class ExcessHealthBar {
    @SpirePostfixPatch
    public static void Insert(AbstractCreature __instance, SpriteBatch sb) {
        if (!(__instance instanceof AbstractPlayer)) return;

        AbstractPlayer player = (AbstractPlayer) __instance;
        AbstractPower power = player.getPower(MaxHP.POWER_ID);

        if (!(power instanceof MaxHP)) return;

        int currentExcess = MaxHP.currentExcessHp;
        int maxExcess = power.amount;

        if (maxExcess <= 0) return;

        float excessFraction = (float) currentExcess / maxExcess;

        float x = player.hb.cX + 168.0F  * Settings.scale;
        float y = player.hb.cY - 128.0F * Settings.scale;

        float healthBarWidth = 196.0F * Settings.scale;
        float barWidth = excessFraction * healthBarWidth;
        float border = 10F * Settings.scale;

        Color backgroundColor = new Color(0.1F, 0.1F, 0.2F, 0.8F);
        Color barColor = new Color(0.3F, 0.3F, 1.0F, 0.9F); // Strong blue

        float barHeight = 20.0F * Settings.scale;
        sb.setColor(backgroundColor);
        sb.draw(ImageMaster.HEALTH_BAR_B, x - border / 2F, y - border / 2F, healthBarWidth + border, barHeight + border);

        sb.setColor(barColor);
        sb.draw(ImageMaster.HEALTH_BAR_B, x, y, barWidth, barHeight);

        String text = currentExcess + " / " + maxExcess;
        BitmapFont font = FontHelper.healthInfoFont;
        Color textColor = Color.WHITE.cpy();
        textColor.a = 1.0F;

        FontHelper.renderFontCentered(sb, font, text, x + healthBarWidth / 2F, y + 10.0F * Settings.scale, textColor);
    }
}