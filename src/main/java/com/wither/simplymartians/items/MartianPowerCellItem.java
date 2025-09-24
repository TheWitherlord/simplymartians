package com.wither.simplymartians.items;

import javax.annotation.Nullable;

import com.wither.simplymartians.entities.ZapBolt;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class MartianPowerCellItem extends Item implements ProjectileItem {
    public MartianPowerCellItem(Item.Properties properties) {
        super(properties);
    }

    public ZapBolt createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new ZapBolt(level, shooter);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        Arrow arrow = new Arrow(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }

    /**
     * Called to determine if this arrow will be infinite when fired. If an arrow is infinite, then the arrow will never be consumed (regardless of enchantments).
     * <p>
     * Only called on the logical server.
     *
     * @param ammo The ammo stack (containing this item)
     * @param bow  The bow stack
     * @param livingEntity The entity who is firing the bow
     * @return True if the arrow is infinite
     */
    public boolean isInfinite(ItemStack ammo, ItemStack bow, net.minecraft.world.entity.LivingEntity livingEntity) {

        return false;
    }
}
