package com.wither.simplymartians.entities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class StrafingRangedAttackGoal<T extends Mob & RangedAttackMob> extends Goal {
    private final T mob;
    private final double speedModifier;
    private final int rangedAttackInterval;
    private final float rangedAttackRadiusSqr;
    private int attackTime = -1;
    private int strafingTime = -1;
    private double strafeDirection;

    public StrafingRangedAttackGoal(T mob, double speedModifier, int rangedAttackInterval, float rangedAttackRadius) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.rangedAttackInterval = rangedAttackInterval;
        this.rangedAttackRadiusSqr = rangedAttackRadius * rangedAttackRadius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.mob.getTarget() != null;
    }

    @Override
    public void start() {
        this.attackTime = this.rangedAttackInterval;
        this.strafingTime = -1;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target == null) return;

        double distanceSqr = this.mob.distanceToSqr(target);
        this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        boolean inAttackRange = distanceSqr <= this.rangedAttackRadiusSqr && this.mob.hasLineOfSight(target);

        if (inAttackRange) {
            // Strafing movement
            if (this.strafingTime == -1) {
                this.strafingTime = 0;
                this.strafeDirection = this.mob.getRandom().nextBoolean() ? 1.0D : -1.0D;
            }
            if (this.strafingTime++ > 60) {
                this.strafeDirection *= -1.0D;
                this.strafingTime = 0;
            }
            Vec3 vector3d = this.mob.getDeltaMovement();
            this.mob.getNavigation().moveTo(this.mob.getX() + this.strafeDirection * vector3d.z, this.mob.getY(), this.mob.getZ() + -this.strafeDirection * vector3d.x, this.speedModifier);
            
            // Ranged attack
            if (--this.attackTime <= 0) {
                this.mob.performRangedAttack(target, 1.0F);
                this.attackTime = this.rangedAttackInterval;
            }
        } else {
            // Not in range, move closer
            this.strafingTime = -1;
            this.mob.getNavigation().moveTo(target, this.speedModifier);
        }
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
    }
}