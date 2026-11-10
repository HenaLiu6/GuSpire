package gugumod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static gugumod.GuguMod.makeID;

public class MaxHP extends BasePower {
    public static final String POWER_ID = makeID("MaxHP");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public static int currentExcessHp = 0;


    private int OverHeal(int heal){
        int hpToMax = owner.maxHealth - owner.currentHealth;
        int overHeal = Math.max(heal - hpToMax, 0);

        currentExcessHp += Math.min(overHeal, owner.getPower(POWER_ID).amount - currentExcessHp);
        return heal - overHeal;
    }

    public MaxHP(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if (owner.getPower(POWER_ID) == null || owner.getPower(POWER_ID).amount == 0){
            currentExcessHp = 0;
        }
        else {
            return;
        }

        int hpToMax = owner.maxHealth - owner.currentHealth;
        int overHeal = Math.max(amount - hpToMax, 0);
        addToBot(new HealAction(owner, owner, amount - overHeal));

        int hpToMaxMax = amount - currentExcessHp;
        if (owner.getPower(POWER_ID) != null ) {
            hpToMaxMax += owner.getPower(POWER_ID).amount;
            //System.out.println("THIS RAN");
        }
        currentExcessHp += Math.min(overHeal, hpToMaxMax);
    }

    @Override
    public void stackPower(int stackAmount) {
        //THIS CODE IS DUPLICATED FROM THE CONSTRUCTOR. For some reason, at the end of the constructor,
        // currentExcessHp is set to current max, regardless of what I set it to.
        super.stackPower(stackAmount);


        int hpToMax = owner.maxHealth - owner.currentHealth;
        int overHeal = Math.max(stackAmount - hpToMax, 0);
        addToBot(new HealAction(owner, owner, stackAmount - overHeal));

        int hpToMaxMax = stackAmount - currentExcessHp;
        if (owner.getPower(POWER_ID) != null ) {
            hpToMaxMax += owner.getPower(POWER_ID).amount;
            //System.out.println("THIS RAN");
        }
        currentExcessHp += Math.min(overHeal, hpToMaxMax);

        updateDescription();
    }

    private int interceptDamage(int damageAmount) {
        int absorbed = Math.min(damageAmount, currentExcessHp);
        currentExcessHp -= absorbed;

        if (absorbed > 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(owner.hb.cX, owner.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
        }

        return damageAmount - absorbed;
    }

    /*@Override //It seems loseHp also triggers when it is not caused by self
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount <= 0 || info.owner == null || info.type == DamageInfo.DamageType.HP_LOSS) {
            return damageAmount;
        }

        return super.onAttacked(info, interceptDamage(damageAmount));
    }*/

    @Override
    public int onHeal(int healAmount) {
        return super.onHeal(OverHeal(healAmount));
    }

    @Override
    public int onLoseHp(int damageAmount) {
        return super.onLoseHp(interceptDamage(damageAmount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + currentExcessHp + DESCRIPTIONS[2];
    }

    //MAKE POWER INVISIBLE
}