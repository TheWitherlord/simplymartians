package com.wither.simplymartians;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.wither.simplymartians.core.init.InitBlocks;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.ModArmorMaterials;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(SimplyMartians.MODID)
//@EventBusSubscriber(modid = WithersWeapons.MODID, bus = Bus.MOD)

public class SimplyMartians {

	public static final String MODID = "simplymartians";
	private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

	
	  public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ALIEN_TAB = CREATIVE_MODE_TABS.register("alien_tab", () -> CreativeModeTab.builder()
	            .title(Component.translatable("itemGroup.simplymartians")) //The language key for the title of your CreativeModeTab
	            .withTabsBefore(CreativeModeTabs.COMBAT)
	            .icon(() -> InitItem.MARTIAN_TECH.get().getDefaultInstance())
	            .displayItems((parameters, output) -> {
	            	List<ItemStack> stacks = InitItem.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
					output.acceptAll(stacks);
				}).build());

	public SimplyMartians(IEventBus modEventBus) {
		
        modEventBus.addListener(this::commonSetup);

        
		InitBlocks.BLOCKS.register(modEventBus);
		InitItem.ITEMS.register(modEventBus);
		InitEntity.ENTITIES_REGISTRY.register(modEventBus);
	       ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
			ModMobEffects.MOB_EFFECT_DEFERRED_REGISTER.register(modEventBus);
			ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);

		

	

        CREATIVE_MODE_TABS.register(modEventBus);
		 NeoForge.EVENT_BUS.register(this);
		 
		 
	}
	
		 
		 private void commonSetup(final FMLCommonSetupEvent event)
		    {
		        // Some common setup code
		        LOGGER.info("HELLO FROM COMMON SETUP");
				
		    }
		   

	
		 @SubscribeEvent
		    public void onServerStarting(ServerStartingEvent event)
		    {
		        // Do something when the server starts
		        LOGGER.info("HELLO from server starting");
		    }

		 // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
		    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
		    public static class ClientModEvents
		    {
		        @SubscribeEvent
		        public static void onClientSetup(FMLClientSetupEvent event)
		        {
		    		//ItemModelProperties.defineRenders();

		            // Some client setup code
		            LOGGER.info("HELLO FROM CLIENT SETUP");
		            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
		        }
		    
		    }
		    public static ResourceLocation prefix(String name) {
		    	return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
		    
}}
