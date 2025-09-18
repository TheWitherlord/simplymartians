package com.wither.simplymartians.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class OvershockParticle extends TextureSheetParticle  {
    private final SpriteSet spriteSet;
    
    // First four parameters are self-explanatory. The SpriteSet parameter is provided by the
    // ParticleProvider, see below. You may also add additional parameters as needed, e.g. xSpeed/ySpeed/zSpeed.
    public OvershockParticle(ClientLevel level, double x, double y, double z, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.spriteSet = spriteSet;
        this.gravity = 0; 
        this.setParticleSpeed(pXSpeed * 0.01 / 2.0, pYSpeed * 0.01, pZSpeed * 0.01 / 2.0);
        
        this.setLifetime(level.random.nextInt(30) + 10);

        this.pickSprite(spriteSet);
    }
    
    @Override
    public void tick() {
        // Set the sprite for the current particle age, i.e. advance the animation.
        this.pickSprite(spriteSet);
        super.tick();
        
    }
    
    @Override
    public int getLightColor(float pPartialTick) {
        return 240;
    }

@Override
public ParticleRenderType getRenderType() {
    return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;

}

}
