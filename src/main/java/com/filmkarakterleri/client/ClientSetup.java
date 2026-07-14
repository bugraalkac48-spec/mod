package com.filmkarakterleri.client;

import com.filmkarakterleri.FilmKarakterleriMod;
import com.filmkarakterleri.entity.ModEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FilmKarakterleriMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.FILM_KARAKTERI.get(), FilmKarakteriRenderer::new);
    }
}
