package gugumod.cards.rare;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import gugumod.cards.BaseCard;
import gugumod.character.GuguCharacter;
import gugumod.util.CardStats;

public class ReaperFriend extends BaseCard {
    public static final String ID = makeID("ReaperFriend");
    private static final CardStats info = new CardStats(
            GuguCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL,
            3
    );

    public ReaperFriend() {
        super(ID, info);

        setInnate(false, true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MonsterGroup all = AbstractDungeon.getMonsters();

        int averageHp = p.currentHealth;
        int livingMonsterCount = 0;
        for (AbstractMonster monster : all.monsters){
            if (!monster.isDeadOrEscaped()) {
                averageHp += monster.currentHealth;
                livingMonsterCount++;
            }
        }

        averageHp = averageHp / (livingMonsterCount + 1); //Assuming 1 player

        for (AbstractMonster monster : all.monsters){
            if (!monster.isDeadOrEscaped()) {
                int healthDiff = monster.currentHealth - averageHp;

                if (healthDiff > 0) {
                    addToBot(new LoseHPAction(monster, p, healthDiff));
                } else {
                    addToBot(new HealAction(monster, p, -healthDiff));
                }
            }
        }

        int healthDiff = p.currentHealth - averageHp;
        if (p.currentHealth > 0) {
            if (healthDiff > 0) {
                addToBot(new LoseHPAction(p, p, healthDiff));
            } else {
                addToBot(new HealAction(p, p, -healthDiff));
            }
        }

    }
}
