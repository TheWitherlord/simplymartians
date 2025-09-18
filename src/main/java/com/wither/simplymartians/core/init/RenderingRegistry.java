package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.rendering.FluxWaveRenderer;
import com.wither.simplymartians.entities.rendering.MartianBruteModel;
import com.wither.simplymartians.entities.rendering.MartianBruteRenderer;
import com.wither.simplymartians.entities.rendering.MartianModel;
import com.wither.simplymartians.entities.rendering.MartianProbeRenderer;
import com.wither.simplymartians.entities.rendering.MartianRenderer;
import com.wither.simplymartians.entities.rendering.ProbeModel;
import com.wither.simplymartians.entities.rendering.ZapBoltRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SimplyMartians.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class RenderingRegistry {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientSetUpEvent(EntityRenderersEvent.RegisterRenderers event) {
    	
    
          
            event.registerEntityRenderer(InitEntity.MARTIAN.get(), MartianRenderer::new);
            event.registerEntityRenderer(InitEntity.MARTIAN_BRUTE.get(), MartianBruteRenderer::new);
            event.registerEntityRenderer(InitEntity.MARTIAN_PROBE.get(), MartianProbeRenderer::new);

            
            
            
            event.registerEntityRenderer(InitEntity.ZAP_BOLT.get(), ZapBoltRenderer::new);
            event.registerEntityRenderer(InitEntity.FLUX_WAVE.get(), FluxWaveRenderer::new);


    }
    
            @SubscribeEvent
            public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            	
                event.registerLayerDefinition(MartianModel.LAYER_LOCATION, MartianModel::createBodyLayer);
                event.registerLayerDefinition(MartianBruteModel.LAYER_LOCATION, MartianBruteModel::createBodyLayer);
                event.registerLayerDefinition(ProbeModel.LAYER_LOCATION, ProbeModel::createBodyLayer);

    

       
    
            }}
