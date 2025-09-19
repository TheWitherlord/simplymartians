package com.wither.simplymartians.core.init;

import java.util.function.Supplier;

import com.wither.simplymartians.SimplyMartians;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


public class InitSoundEvents {
	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SimplyMartians.MODID);

	private static boolean isInitialised;



	public static final Supplier<SoundEvent> PEW = registerSoundEvent("item.martian_zapper.fire");
	public static final Supplier<SoundEvent> MARTIAN_AMBIENT = registerSoundEvent("mob.martian.ambient");
	public static final Supplier<SoundEvent> MARTIAN_HURT = registerSoundEvent("mob.martian.hurt");
	public static final Supplier<SoundEvent> MARTIAN_DEATH = registerSoundEvent("mob.martian.death");

	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialise(final IEventBus modEventBus) {
		if (isInitialised) {
			throw new IllegalStateException("Already initialised");
		}

		SOUND_EVENTS.register(modEventBus);

		isInitialised = true;
	}

	/**
	 * Registers a sound event.
	 *
	 * @param soundName The sound event's name, without the testmod3 prefix
	 * @return A RegistryObject reference to the SoundEvent
	 */
	private static Supplier<SoundEvent> registerSoundEvent(final String soundName) {
		return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, soundName)));
	}
	private static DeferredHolder<SoundEvent, SoundEvent> createEvent(String sound) {
		return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(SimplyMartians.prefix(sound)));
	}
}