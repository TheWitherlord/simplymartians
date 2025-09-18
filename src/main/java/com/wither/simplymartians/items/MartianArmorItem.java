package com.wither.simplymartians.items;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.DispenserBlock;

public class MartianArmorItem extends ArmorItem {
	
	
	 protected final ArmorItem.Type type;
	    protected final Holder<ArmorMaterial> material;
	    private final Supplier<ItemAttributeModifiers> defaultModifiers;

	 //@Override
    public MartianArmorItem(Holder<ArmorMaterial> pMaterial, ArmorItem.Type pType, Item.Properties pProperties) {
        super(pMaterial, pType, pProperties);
        this.material = pMaterial;
        this.type = pType;
        DispenserBlock.registerBehavior(this, DISPENSE_ITEM_BEHAVIOR);
        this.defaultModifiers = Suppliers.memoize(
            () -> {
                int i = pMaterial.value().getDefense(pType);
                float f = pMaterial.value().toughness();
                ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(pType.getSlot());
                EquipmentSlotGroup feet = EquipmentSlotGroup.bySlot(EquipmentSlot.FEET);

                ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + pType.getName());
                itemattributemodifiers$builder.add(
                    Attributes.ARMOR, new AttributeModifier(resourcelocation, (double)i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                );
                itemattributemodifiers$builder.add(
                    Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, (double)f, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                );
                itemattributemodifiers$builder.add(
                        Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(resourcelocation, (double)3, AttributeModifier.Operation.ADD_VALUE), feet
                    );
                itemattributemodifiers$builder.add(
                        Attributes.OXYGEN_BONUS, new AttributeModifier(resourcelocation, (double)1, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                    );
                itemattributemodifiers$builder.add(
                        Attributes.GRAVITY, new AttributeModifier(resourcelocation, (double)-0.01, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                    );
                float f1 = pMaterial.value().knockbackResistance();
                if (f1 > 0.0F) {
                    itemattributemodifiers$builder.add(
                        Attributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier(resourcelocation, (double)f1, AttributeModifier.Operation.ADD_VALUE),
                        equipmentslotgroup
                    );
                }

                return itemattributemodifiers$builder.build();
            }
        );
    }
    
    

    
    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.defaultModifiers.get();
    }
	
    
   


   

}