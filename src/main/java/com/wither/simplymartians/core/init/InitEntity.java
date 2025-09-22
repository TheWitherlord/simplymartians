package com.wither.simplymartians.core.init;

import java.util.function.Supplier;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.FluxWave;
import com.wither.simplymartians.entities.MartianBruteEntity;
import com.wither.simplymartians.entities.MartianProbeEntity;
import com.wither.simplymartians.entities.SimpleMartianEntity;
import com.wither.simplymartians.entities.ZapBolt;
import com.wither.simplymartians.entities.ZombieMartianBruteEntity;
import com.wither.simplymartians.entities.ZombieMartianEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = SimplyMartians.MODID, bus = Bus.MOD)
public class InitEntity {
	public static final DeferredRegister<EntityType<?>> ENTITIES_REGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE,
			SimplyMartians.MODID);

	


	public static final Supplier<EntityType<ZapBolt>> ZAP_BOLT = ENTITIES_REGISTRY.register("zap_bolt",
			() -> EntityType.Builder.<ZapBolt>of(ZapBolt::new, MobCategory.MISC).sized(0.5F, 0.5F)
					.clientTrackingRange(4).updateInterval(10).build(prefix("zap_bolt")));
	
	
	public static final Supplier<EntityType<SimpleMartianEntity>> MARTIAN = ENTITIES_REGISTRY
			.register("martian", () -> EntityType.Builder.of(SimpleMartianEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 1.99F).clientTrackingRange(8).eyeHeight(1.55f).build(prefix("martian")));
	
	public static final Supplier<EntityType<MartianBruteEntity>> MARTIAN_BRUTE = ENTITIES_REGISTRY
			.register("martian_brute", () -> EntityType.Builder.of(MartianBruteEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 2F).clientTrackingRange(8).eyeHeight(1.6f).build(prefix("martian_brute")));
	
	public static final Supplier<EntityType<ZombieMartianEntity>> ZOMBIE_MARTIAN = ENTITIES_REGISTRY
			.register("zombie_martian", () -> EntityType.Builder.of(ZombieMartianEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 1.99F).clientTrackingRange(8).eyeHeight(1.55f).build(prefix("zombie_martian")));
	
	public static final Supplier<EntityType<ZombieMartianBruteEntity>> ZOMBIE_MARTIAN_BRUTE = ENTITIES_REGISTRY
			.register("zombie_martian_brute", () -> EntityType.Builder.of(ZombieMartianBruteEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 2F).clientTrackingRange(8).eyeHeight(1.6f).build(prefix("zombie_martian_brute")));
	
	public static final Supplier<EntityType<FluxWave>> FLUX_WAVE = ENTITIES_REGISTRY.register("flux_wave",
			() -> EntityType.Builder.<FluxWave>of(FluxWave::new, MobCategory.MISC).sized(2F, 1F)
					.clientTrackingRange(4).updateInterval(10).build(prefix("flux_wave")));
	
	public static final Supplier<EntityType<MartianProbeEntity>> MARTIAN_PROBE = ENTITIES_REGISTRY
			.register("martian_probe", () -> EntityType.Builder.of(MartianProbeEntity::new, MobCategory.MONSTER)
					.sized(0.7F, 0.7F).clientTrackingRange(8).eyeHeight(0.5f).build(prefix("martian_probe")));
	
	
	private static String prefix(String path) {
		return SimplyMartians.MODID + "." + path;
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {

		event.put(MARTIAN.get(), SimpleMartianEntity.createAttributes().build());
		event.put(MARTIAN_BRUTE.get(), MartianBruteEntity.createAttributes().build());
		event.put(MARTIAN_PROBE.get(), MartianProbeEntity.createAttributes().build());
		event.put(ZOMBIE_MARTIAN.get(), ZombieMartianEntity.createAttributes().build());
		event.put(ZOMBIE_MARTIAN_BRUTE.get(), ZombieMartianBruteEntity.createAttributes().build());

	}
		@SubscribeEvent
		public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
			event.register(MARTIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
					Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
			event.register(MARTIAN_BRUTE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
					Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

	}
}