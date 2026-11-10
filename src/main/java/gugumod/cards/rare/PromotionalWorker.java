package gugumod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.powers.AmanoIns;
import gugumod.powers.ErunIns;
import gugumod.powers.MakiIns;
import gugumod.powers.RaidenIns;
import gugumod.util.CardStats;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionalWorker extends BaseCard {
    public static final String ID = makeID("PromotionalWorker");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -1
    );

    public PromotionalWorker() {
        super(ID, info);
        // x + 1 when upgraded
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        List<AbstractPower> potentialPowers = Arrays.asList(
                new RaidenIns(p, 0),
                new MakiIns(p, 0),
                new AmanoIns(p, 0),
                new ErunIns(p, 0)
        );

        List<AbstractPower> missingPowers = potentialPowers.stream()
            .filter(power -> !p.hasPower(power.ID))
            .collect(Collectors.toList());

        Collections.shuffle(missingPowers, new java.util.Random(AbstractDungeon.cardRandomRng.randomLong()));

        int x = this.energyOnUse;
        addToBot(new LoseEnergyAction(x));

        if (this.upgraded) x++; // Upgrade effect

        // Apply up to X missing powers
        for (int i = 0; i < Math.min(x, missingPowers.size()); i++) {
            AbstractPower powerToApply = missingPowers.get(i);
            addToBot(new ApplyPowerAction(p, p, powerToApply));
        }
    }
}