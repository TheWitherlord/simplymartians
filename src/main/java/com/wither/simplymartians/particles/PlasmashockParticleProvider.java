package com.wither.simplymartians.particles;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = SimplyMartians.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class PlasmashockParticleProvider implements ParticleProvider<SimpleParticleType> {
    // A set of particle sprites.
    private final SpriteSet spriteSet;
    
    // The registration function passes a SpriteSet, so we accept that and store it for further use.
    public PlasmashockParticleProvider(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }
    
    // This is where the magic happens. We return a new particle each time this method is called!
    // The type of the first parameter matches the generic type passed to the super interface.
    @Override
    public PlasmashockParticle createParticle(SimpleParticleType type, ClientLevel level,
            double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        // We don't use the type and speed, and pass in everything else. You may of course use them if needed.
        return new PlasmashockParticle(level, x, y, z, zSpeed, zSpeed, zSpeed, spriteSet);
        
    }
    
   
    
    
    
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        // There are multiple ways to register providers, all differing in the functional type they provide in the
        // second parameter. For example, #registerSpriteSet represents a Function<SpriteSet, ParticleProvider<?>>:
        event.registerSpriteSet(ModParticleTypes.PLASMASHOCK.get(), PlasmashockParticleProvider::new);

        // Other methods include #registerSprite, which is essentially a Supplier<TextureSheetParticle>,
        // and #registerSpecial, which maps to a Supplier<Particle>. See the source code of the event for further info.
    }
}