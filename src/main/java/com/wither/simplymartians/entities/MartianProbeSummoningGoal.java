package com.wither.simplymartians.entities;

import java.util.EnumSet;

import com.wither.simplymartians.core.init.InitEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class MartianProbeSummoningGoal extends Goal {
    private final UFOBossEntity boss;
    private int summonCooldown;
    private final int maxMinions;

    public MartianProbeSummoningGoal(UFOBossEntity boss, int maxMinions) {
        this.boss = boss;
        this.maxMinions = maxMinions;
        this.summonCooldown = 0;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    // This method determines when the goal can start.
    @Override
    public boolean canUse() {
        return this.boss.getTarget() != null && this.summonCooldown <= 0;
    }

    // This method determines when the goal should continue.
    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    // This method is called every tick while the goal is active.
    @Override
    public void tick() {
        LivingEntity target = this.boss.getTarget();
        if (target == null) return;

        // Check the number of existing minions
        if (this.boss.level().getEntitiesOfClass(MartianProbeEntity.class, this.boss.getBoundingBox().inflate(16.0)).size() < maxMinions) {
            // Summon a minion
            summonMinion();
            this.summonCooldown = 100; // Set a 5-second cooldown (100 ticks)
        } else {
            this.summonCooldown--;
        }
    }

    private void summonMinion() {
        if (!this.boss.level().isClientSide()) {
        	MartianProbeEntity minion = new MartianProbeEntity(InitEntity.MARTIAN_PROBE.get(), this.boss.level());
            double x = this.boss.getX() + (boss.getRandom().nextDouble() - 0.5) * 4.0;
            double y = this.boss.getY() + 3.0;
            double z = this.boss.getZ() + (boss.getRandom().nextDouble() - 0.5) * 4.0;

            minion.setPos(x, y, z);
            this.boss.level().addFreshEntity(minion);
        }
    }
}