package com.wither.simplymartians.items;

import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.util.TagInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

/**
 * A tool that can function as a sword, pickaxe, axe or shovel.
 * <p>
 * Test for this thread:
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2550421-how-to-make-a-tool-e-g-a-sword-have-the-abilities
 *
 * @author Choonster
 */
public class MartianDrillItem extends DiggerItem {

	private static final int COOLDOWN = 25;
	private static final float DIG_SPEED_COBWEB = 15.0f;
	private static final float DIG_SPEED_SWORD = 1.5f;
	private static final float DIG_SPEED_DEFAULT = 1.0f;
	public MartianDrillItem(int i, float f, final Tier tier, final Item.Properties properties) {
		super(tier, TagInit.MARTIAN_MINEABLE, properties.component(DataComponents.TOOL, createTool(tier)));
	}

	protected int getCooldown(final InteractionHand interactionHand) {
		return COOLDOWN;
	}

	public static ItemAttributeModifiers createAttributes(Tier pTier, float pAttackDamage, float pAttackSpeed,
			float f) {
		return ItemAttributeModifiers.builder()
				.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
						(double) (pAttackDamage + pTier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_SPEED,
						new AttributeModifier(BASE_ATTACK_SPEED_ID, (double) pAttackSpeed,
								AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.add(Attributes.BLOCK_INTERACTION_RANGE,
						new AttributeModifier(ResourceLocation.withDefaultNamespace("player.entity_interaction_range"),
								2.0, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.build();
	}

	@Override
	public boolean canPerformAction(final ItemStack stack, final ItemAbility toolAction) {
		return Stream
				.of(ItemAbilities.DEFAULT_SWORD_ACTIONS, ItemAbilities.DEFAULT_PICKAXE_ACTIONS,
						ItemAbilities.DEFAULT_AXE_ACTIONS, ItemAbilities.DEFAULT_SHOVEL_ACTIONS)
				.anyMatch(toolActions -> toolActions.contains(toolAction));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.PASS;
		
			
		} else {
			Player player = context.getPlayer();

			final int cooldown = getCooldown(context.getHand());
			if (cooldown > 0) {
				player.getCooldowns().addCooldown(this, cooldown);
			}

			if (blockstate.is(TagInit.MARTIAN_MINEABLE)) {
				for (int x = -1; x <= 1; x++) {
					for (int y = -1; y <= 1; y++) {
						for (int z = -1; z <= 1; z++) {
							BlockState checkBlock = level.getBlockState(blockpos.offset(x, y, z));
							if (checkBlock.is(TagInit.MARTIAN_MINEABLE)) {
								if (checkBlock.getBlock().canHarvestBlock(checkBlock, level, blockpos.offset(x, y, z),
										player)) {
									level.destroyBlock(blockpos.offset(x, y, z), true);
									level.playSound(player, blockpos, InitSoundEvents.FLUXIUM_DRILL.get(), SoundSource.PLAYERS, 0.3F,
											1.0F);

								}
							}
						}
					}
				}
				context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));

			}
            player.startUsingItem(context.getHand());

			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}
	
	
	 @Override
	    public int getUseDuration(ItemStack stack, LivingEntity entity) {
	        return 20;
	    }

	    /**
	     * Returns the action that specifies what animation to play when the item is being used.
	     */
	    @Override
	    public UseAnim getUseAnimation(ItemStack stack) {
	        return UseAnim.BOW;
	    }
	

	

	private static Tool createTool(final Tier tier) {
		final var rules = ImmutableList.<Tool.Rule>builder()
				.add(Tool.Rule.deniesDrops(tier.getIncorrectBlocksForDrops()));

		final var minesAndDrops = Stream
				.of(BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_HOE, BlockTags.MINEABLE_WITH_PICKAXE,
						BlockTags.MINEABLE_WITH_SHOVEL)
				.map(tag -> Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_AXE, tier.getSpeed()));

		rules.addAll(minesAndDrops.iterator());

		rules.add(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), DIG_SPEED_COBWEB));
		rules.add(Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, DIG_SPEED_SWORD));

		return new Tool(rules.build(), DIG_SPEED_DEFAULT, 1);
	}
}