package com.wither.simplymartians.events;

import com.wither.simplymartians.core.init.InitItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber
public class EngineerGogglesSight {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        // Check if the game is running on the server
        if (!player.level().isClientSide()) {
            // Check if the player is wearing your full armor set
            if (isWearingFullSet(player)) {
                // Find nearby hostile entities
                double radius = 10.0; // Set your desired radius
                List<LivingEntity> nearbyEntities = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(radius),
                    entity -> entity instanceof LivingEntity && !entity.is(player) && entity.isAlive()
                );

                // Apply the glowing effect to nearby entities
                for (LivingEntity entity : nearbyEntities) {
                    entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 0, false, false));
                }
            }
        }
    }

    private static boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(InitItem.ENGINEER_GOGGLES.get());
    }
}