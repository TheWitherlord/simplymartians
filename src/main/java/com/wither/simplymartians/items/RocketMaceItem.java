package com.wither.simplymartians.items;

import java.util.List;
import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.ModMobEffects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RocketMaceItem extends TieredItem {
    public static final float SMASH_ATTACK_FALL_THRESHOLD = 4F;
    public static final float SMASH_ATTACK_KNOCKBACK_RADIUS = 6.0F;

    public RocketMaceItem(Tier tier, Item.Properties pProperties) {
        super(tier, pProperties);
    }
    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(InitItem.MARTIAN_TECH) || super.isValidRepairItem(pToRepair, pRepair);
    }

    public static @NotNull ItemAttributeModifiers createAttributes(Tier tier, int damage, float speed, float f, float g) {
        return ItemAttributeModifiers.builder()
        		 .add(
        	                Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 8.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
        	            )
        		 
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                
               
                .add(
                        Attributes.SWEEPING_DAMAGE_RATIO,
                        new AttributeModifier(ResourceLocation.withDefaultNamespace("player.sweeping_damage_ratio"), 0.50, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                
               
                .add(
     	                Attributes.ATTACK_KNOCKBACK, new AttributeModifier((ResourceLocation.withDefaultNamespace("player.attack_knockback")), 3.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
     	            )
                
                .build();
    }
    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2);
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof ServerPlayer serverplayer && canSmashAttack(serverplayer)) {
            ServerLevel serverlevel = (ServerLevel)pAttacker.level();
            if (serverplayer.isIgnoringFallDamageFromCurrentImpulse() && serverplayer.currentImpulseImpactPos != null) {
                if (serverplayer.currentImpulseImpactPos.y > serverplayer.position().y) {
                    serverplayer.currentImpulseImpactPos = serverplayer.position();
                }
            } else {
                serverplayer.currentImpulseImpactPos = serverplayer.position();
            }

            serverplayer.setIgnoreFallDamageFromCurrentImpulse(true);
            serverplayer.setDeltaMovement(serverplayer.getDeltaMovement().with(Direction.Axis.Y, 0.01F));
            serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
            if (pTarget.onGround()) {
                serverplayer.setSpawnExtraParticlesOnFall(true);
                SoundEvent soundevent = serverplayer.fallDistance > 5.0F ? SoundEvents.MACE_SMASH_GROUND_HEAVY : SoundEvents.MACE_SMASH_GROUND;
                serverlevel.playSound(
                    null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), soundevent, serverplayer.getSoundSource(), 1.0F, 1.0F
                );
            } else {
                serverlevel.playSound(
                    null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), SoundEvents.MACE_SMASH_AIR, serverplayer.getSoundSource(), 1.0F, 1.0F
                );
            }

            knockback(serverlevel, serverplayer, pTarget);
        }

        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, EquipmentSlot.MAINHAND);
        if (canSmashAttack(pAttacker)) {
            pAttacker.resetFallDistance();
        }
    }

    

    @Override
    public float getAttackDamageBonus(Entity pTarget, float pDamage, DamageSource pDamageSource) {
        if (pDamageSource.getDirectEntity() instanceof LivingEntity livingentity) {
            if (!canSmashAttack(livingentity)) {
                return 0.0F;
            } else {
                float f3 = 3.0F;
                float f = 8.0F;
                float f1 = livingentity.fallDistance;
                float f2;
                if (f1 <= 3.0F) {
                    f2 = 4.0F * f1;
                } else if (f1 <= 8.0F) {
                    f2 = 12.0F + 2.0F * (f1 - 3.0F);
                } else {
                    f2 = 22.0F + f1 - 8.0F;
                }

                return livingentity.level() instanceof ServerLevel serverlevel
                    ? f2 + EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingentity.getWeaponItem(), pTarget, pDamageSource, 0.0F) * f1
                    : f2;
            }
        } else {
            return 0.0F;
        }
    }

    private static void knockback(Level pLevel, Player pPlayer, Entity pEntity) {
        pLevel.levelEvent(2013, pEntity.getOnPos(), 750);
        pLevel.getEntitiesOfClass(LivingEntity.class, pEntity.getBoundingBox().inflate(6.0), knockbackPredicate(pPlayer, pEntity))
            .forEach(p_347296_ -> {
                Vec3 vec3 = p_347296_.position().subtract(pEntity.position());
                double d0 = getKnockbackPower(pPlayer, p_347296_, vec3);
                Vec3 vec31 = vec3.normalize().scale(d0);
                if (d0 > 0.0) {
                    p_347296_.push(vec31.x, 1.2F, vec31.z);
                    p_347296_.hurt(p_347296_.damageSources().playerAttack(pPlayer),  5);
                    p_347296_.addEffect(new MobEffectInstance(ModMobEffects.PLASMA, 55, 0, true, true));

                    if (p_347296_ instanceof ServerPlayer serverplayer) {
                        serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
                    }
                }
            });
    }

    private static Predicate<LivingEntity> knockbackPredicate(Player pPlayer, Entity pEntity) {
        return p_344407_ -> {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag6;
            label62: {
                flag = !p_344407_.isSpectator();
                flag1 = p_344407_ != pPlayer && p_344407_ != pEntity;
                flag2 = !pPlayer.isAlliedTo(p_344407_);
                if (p_344407_ instanceof TamableAnimal tamableanimal && tamableanimal.isTame() && pPlayer.getUUID().equals(tamableanimal.getOwnerUUID())) {
                    flag6 = true;
                    break label62;
                }

                flag6 = false;
            }

            boolean flag3;
            label55: {
                flag3 = !flag6;
                if (p_344407_ instanceof ArmorStand armorstand && armorstand.isMarker()) {
                    flag6 = false;
                    break label55;
                }

                flag6 = true;
            }

            boolean flag4 = flag6;
            boolean flag5 = pEntity.distanceToSqr(p_344407_) <= Math.pow(6.0, 2.0);
            return flag && flag1 && flag2 && flag3 && flag4 && flag5;
        };
    }

    private static double getKnockbackPower(Player pPlayer, LivingEntity pEntity, Vec3 pEntityPos) {
        return (6.0 - pEntityPos.length())
            * 1F
            * (double)(pPlayer.fallDistance > 5.0F ? 2 : 1)
            * (1.0 - pEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
    }

    public static boolean canSmashAttack(LivingEntity pEntity) {
        return pEntity.fallDistance > 2F  && !pEntity.isFallFlying();
        

    }
    

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 40;
    }

    // Sets the animation for the item.
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR; // A good "charging" animation
    }

    // Initiates the item's use.
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(itemstack);
    }

    // Adds visual/audio feedback during charging.
    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (pLevel.isClientSide && pLivingEntity instanceof Player pPlayer) {
            if (pRemainingUseDuration % 12 == 0) {
                pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    // The method that executes the ground slam.
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide && pLivingEntity instanceof Player pPlayer) {
            // Check for the targeted block
            HitResult hitResult = pPlayer.pick(10.0, 0.0F, false); // Raytrace up to 10 blocks away

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos targetPos = ((BlockHitResult) hitResult).getBlockPos();
                net.minecraft.world.level.block.state.BlockState blockState = pLevel.getBlockState(targetPos);

                double radius = 5.0; 
                AABB knockbackZone = new AABB(
                    targetPos.getX() - radius, targetPos.getY() - radius, targetPos.getZ() - radius,
                    targetPos.getX() + radius, targetPos.getY() + radius, targetPos.getZ() + radius
                );

                List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, knockbackZone);
                
                for (LivingEntity target : entities) {
                    if (target == pPlayer) {
                        continue;
                    }
                    
                    boolean isPet = false;
                    if (target instanceof OwnableEntity ownableEntity) {
                        if (ownableEntity.getOwner() != null && ownableEntity.getOwner().equals(pPlayer)) {
                            isPet = true;
                        }
                    }

                    if (!isPet) {
                        // Knockback direction is from the targeted block, not the player
                        Vec3 direction = target.position().subtract(targetPos.getCenter()).normalize();
                        
                        double knockbackStrength = 2.0; 
                        double verticalPush = 1.5; 

                        target.setDeltaMovement(
                            direction.x * knockbackStrength,
                            verticalPush,
                            direction.z * knockbackStrength
                        );
                        // Damage is applied relative to the block slam
                        target.hurt(pPlayer.damageSources().mobAttack(pPlayer), 10.0f);
                    }
                }
                
                // Play particle and sound effects at the target block
                pLevel.playSound(null, targetPos, SoundEvents.MACE_SMASH_GROUND_HEAVY, SoundSource.PLAYERS, 1.0F, 1.0F);
                	  if (pLevel instanceof ServerLevel serverLevel) {
                          serverLevel.sendParticles(ParticleTypes.EXPLOSION, targetPos.getX(), targetPos.getY(), targetPos.getZ(), 5, 0.5, 0.5, 0.5, 0.0);
                          serverLevel.sendParticles(ParticleTypes.FLAME, targetPos.getX(), targetPos.getY(), targetPos.getZ(), 5, 0.5, 0.5, 0.5, 0.0);

                          serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                              targetPos.getX() + 0.5, targetPos.getY() + 1.0, targetPos.getZ() + 0.5,
                              1000, // Count of particles
                              0.8, 0.8, 0.8, // Spread
                              0.1 // Speed
                          );
                      }                

          	        pStack.hurtAndBreak(1, pPlayer, EquipmentSlot.MAINHAND);
           
            }
        }
        return pStack;
    }

 
	   
	   @Override
	    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
	        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility);
	    }
	
}
