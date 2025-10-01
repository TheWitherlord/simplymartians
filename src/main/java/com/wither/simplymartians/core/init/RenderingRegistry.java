package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.rendering.BiggerPlasmaBallRenderer;
import com.wither.simplymartians.entities.rendering.DistressBeaconModel;
import com.wither.simplymartians.entities.rendering.DistressBeaconRenderer;
import com.wither.simplymartians.entities.rendering.EnergyBallRenderer;
import com.wither.simplymartians.entities.rendering.FluxWaveRenderer;
import com.wither.simplymartians.entities.rendering.MartianBoltRenderer;
import com.wither.simplymartians.entities.rendering.MartianBruteModel;
import com.wither.simplymartians.entities.rendering.MartianBruteRenderer;
import com.wither.simplymartians.entities.rendering.MartianEnforcerRenderer;
import com.wither.simplymartians.entities.rendering.MartianEngineerModel;
import com.wither.simplymartians.entities.rendering.MartianEngineerRenderer;
import com.wither.simplymartians.entities.rendering.MartianGrenadeRenderer;
import com.wither.simplymartians.entities.rendering.MartianModel;
import com.wither.simplymartians.entities.rendering.MartianProbeRenderer;
import com.wither.simplymartians.entities.rendering.MartianRenderer;
import com.wither.simplymartians.entities.rendering.MartianSurveyorRenderer;
import com.wither.simplymartians.entities.rendering.MottLaserRenderer;
import com.wither.simplymartians.entities.rendering.PlasmaBallRenderer;
import com.wither.simplymartians.entities.rendering.ProbeModel;
import com.wither.simplymartians.entities.rendering.TeslaBotModel;
import com.wither.simplymartians.entities.rendering.TeslaBotRenderer;
import com.wither.simplymartians.entities.rendering.UFOModel;
import com.wither.simplymartians.entities.rendering.ZapBoltRenderer;
import com.wither.simplymartians.entities.rendering.ZombieMartianBruteModel;
import com.wither.simplymartians.entities.rendering.ZombieMartianBruteRenderer;
import com.wither.simplymartians.entities.rendering.ZombieMartianModel;
import com.wither.simplymartians.entities.rendering.ZombieMartianRenderer;

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
            event.registerEntityRenderer(InitEntity.MARTIAN_ENGINEER.get(), MartianEngineerRenderer::new);
            event.registerEntityRenderer(InitEntity.TESLA_BOT.get(), TeslaBotRenderer::new);
            event.registerEntityRenderer(InitEntity.MARTIAN_DISTRESS_BEACON.get(), DistressBeaconRenderer::new);
            event.registerEntityRenderer(InitEntity.MARTIAN_ENFORCER.get(), MartianEnforcerRenderer::new);

            event.registerEntityRenderer(InitEntity.MARTIAN_SURVEYOR.get(), MartianSurveyorRenderer::new);

            event.registerEntityRenderer(InitEntity.ZOMBIE_MARTIAN.get(), ZombieMartianRenderer::new);
            event.registerEntityRenderer(InitEntity.ZOMBIE_MARTIAN_BRUTE.get(), ZombieMartianBruteRenderer::new);

            
            event.registerEntityRenderer(InitEntity.MARTIAN_BOLT.get(), MartianBoltRenderer::new);
            event.registerEntityRenderer(InitEntity.ZAP_BOLT.get(), ZapBoltRenderer::new);
            event.registerEntityRenderer(InitEntity.FLUX_WAVE.get(), FluxWaveRenderer::new);
            event.registerEntityRenderer(InitEntity.MARTIAN_GRENADE.get(), MartianGrenadeRenderer::new);
            event.registerEntityRenderer(InitEntity.PLASMA_BALL.get(), PlasmaBallRenderer::new);
            event.registerEntityRenderer(InitEntity.BIG_PLASMA_BALL.get(), BiggerPlasmaBallRenderer::new);
            event.registerEntityRenderer(InitEntity.ENERGY_BALL.get(), EnergyBallRenderer::new);
            event.registerEntityRenderer(InitEntity.MOTT_LASER.get(), MottLaserRenderer::new);


    }
    
            @SubscribeEvent
            public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            	
                event.registerLayerDefinition(MartianModel.LAYER_LOCATION, MartianModel::createBodyLayer);
                event.registerLayerDefinition(MartianEngineerModel.LAYER_LOCATION, MartianEngineerModel::createBodyLayer);
                event.registerLayerDefinition(MartianBruteModel.LAYER_LOCATION, MartianBruteModel::createBodyLayer);
                event.registerLayerDefinition(ProbeModel.LAYER_LOCATION, ProbeModel::createBodyLayer);
                event.registerLayerDefinition(ZombieMartianModel.LAYER_LOCATION, ZombieMartianModel::createBodyLayer);
                event.registerLayerDefinition(ZombieMartianBruteModel.LAYER_LOCATION, ZombieMartianBruteModel::createBodyLayer);
                event.registerLayerDefinition(UFOModel.LAYER_LOCATION, UFOModel::createBodyLayer);
                event.registerLayerDefinition(TeslaBotModel.LAYER_LOCATION, TeslaBotModel::createBodyLayer);
                event.registerLayerDefinition(DistressBeaconModel.LAYER_LOCATION, DistressBeaconModel::createBodyLayer);


       
    
            }}
