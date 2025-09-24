package com.wither.simplymartians.items;

import java.util.List;
import java.util.Optional;

import com.wither.simplymartians.SimplyMartians;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class MeteorSummonerItem extends Item {
    // The registry name of your structure template.
    private static final ResourceLocation STRUCTURE_LOCATION = ResourceLocation
			.fromNamespaceAndPath(SimplyMartians.MODID, "new_meteor");
   
  
    
    public MeteorSummonerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) pContext.getLevel();
            BlockPos clickedPos = pContext.getClickedPos();

            StructureTemplateManager templateManager = serverLevel.getStructureManager();
            Optional<StructureTemplate> optionalTemplate = templateManager.get(STRUCTURE_LOCATION);

            if (optionalTemplate.isPresent()) {
                StructureTemplate template = optionalTemplate.get();
                BlockPos spawnPos = clickedPos.below(2); // Places the structure above the clicked block.
                spawnParticles(serverLevel, clickedPos);

                StructurePlaceSettings settings = new StructurePlaceSettings();
                //.setRotation(Rotation.getRandom(serverLevel.random));

                template.placeInWorld(serverLevel, spawnPos, spawnPos, settings, serverLevel.random, 2);

                if (pContext.getPlayer() != null) {
                    // Consume one item upon successful placement.
                    serverLevel.playSound(null, spawnPos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1.0f, 1.0f);
        			Player player = pContext.getPlayer();

                    final int cooldown = getCooldown(pContext.getHand());
        			if (cooldown > 0) {
        				player.getCooldowns().addCooldown(this, cooldown);
        			}
                    pContext.getPlayer().getItemInHand(pContext.getHand()).shrink(1);
                }
                return InteractionResult.sidedSuccess(false);
            }
        }
        return InteractionResult.FAIL;
    }
    
    private int getCooldown(InteractionHand hand) {
		// TODO Auto-generated method stub
		return 1500;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.simplymartians.meteor.tooltip").withStyle(ChatFormatting.GOLD));

        }
    
    private void spawnParticles(ServerLevel serverLevel, BlockPos pos) {
        serverLevel.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 250, 0.5, 0.5, 0.5, 1.0);
        serverLevel.sendParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 250, 0.5, 0.5, 0.5, 1.0);

    }

    
}