package com.wither.simplymartians.entities;

import java.util.EnumSet;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class EnforcerAttackGoal<T extends Monster & RangedAttackMob> extends Goal {
    private final T mob;
    private LivingEntity target;
    private final double speedModifier;
    private int attackIntervalMin;
    private int attackIntervalMax;
    private int attackTime = -1;
    private int strafeTime = 0;
    private final float attackRadius;
    private final float meleeSwitchRadius;

    public EnforcerAttackGoal(T mob, double speedModifier, int attackIntervalMin, int attackIntervalMax, float attackRadius, float meleeSwitchRadius) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.attackIntervalMin = attackIntervalMin;
        this.attackIntervalMax = attackIntervalMax;
        this.attackRadius = attackRadius * attackRadius; 
        this.meleeSwitchRadius = meleeSwitchRadius * meleeSwitchRadius; 
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        } else {
            this.target = target;
            return this.mob.distanceToSqr(this.target) > this.meleeSwitchRadius;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() && this.mob.distanceToSqr(this.target) > this.meleeSwitchRadius;
    }

    @Override
    public void tick() {
        double distanceSqr = this.mob.distanceToSqr(this.target);
        boolean inAttackRange = distanceSqr <= this.attackRadius;

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        // Strafing logic
        if (inAttackRange) {
            this.strafeTime++;
            if (this.strafeTime >= 60) {
                // Change direction every 3 seconds (60 ticks)
                this.strafeTime = 0;
                this.mob.getNavigation().moveTo(this.mob.getX() + (this.mob.getRandom().nextBoolean() ? 10.0D : -10.0D), this.mob.getY(), this.mob.getZ() + (this.mob.getRandom().nextBoolean() ? 10.0D : -10.0D), this.speedModifier);
            }
        } else {
            // Move closer
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            this.strafeTime = 0;
        }

        // Ranged attack logic
        this.attackTime--;
        if (this.attackTime <= 0 && inAttackRange) {
            this.mob.performRangedAttack(this.target, 1.0F);
            this.attackTime = this.mob.getRandom().nextInt(this.attackIntervalMax - this.attackIntervalMin + 1) + this.attackIntervalMin;
        }
    }
}