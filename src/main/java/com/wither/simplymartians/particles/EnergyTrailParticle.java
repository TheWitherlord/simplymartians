package com.wither.simplymartians.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;

public class EnergyTrailParticle  extends SonicBoomParticle {

	protected EnergyTrailParticle(ClientLevel level, double x, double y, double z, double quadSizeMultiplier,
			SpriteSet sprites) {
		super(level, x, y, z, quadSizeMultiplier, sprites);
        this.quadSize = 1.0F;

		
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
