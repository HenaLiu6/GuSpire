package gugumod.cards.common;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class Member extends BaseCard {
    public static final String ID = makeID("Member");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final String[] NAMES = new String[] {
            "Henai", "Raiden", "Mira", "Yangwei de Goudan", "Huijin_official", "skFreya Kaisa", "Bunny Ba Dagai", "Yi Dashi Vegetable", "Cang Lan Lanzilanzi"
    }; //Chinese characters don't work in card titles, unless language is set to chinese.

    private static final String[] NAMES_CN = new String[] {
            "Henai", "Raiden", "Mira", "养胃的狗蛋", "灰烬_offical", "skFreya凯撒", "Bunny吧大概", "易大师vegetable", "沧岚岚岚子"
    };
    private String cardName;

    public Member() {
        super(ID, info);
        if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
            cardName = NAMES_CN[MathUtils.random(NAMES.length - 1)];
        }
        else {
            cardName = NAMES[MathUtils.random(NAMES.length - 1)];
        }
        this.name = "Member: " + cardName;
        initializeTitle();
        setExhaust(true);
        setMagic(20, 10);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 1));
        addToTop(new GainGoldAction(magicNumber));
    }
}
