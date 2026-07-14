package com.filmkarakterleri;

import com.filmkarakterleri.entity.ModEntities;
import com.filmkarakterleri.event.ChatReplikHandler;
import com.filmkarakterleri.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FilmKarakterleriMod.MODID)
public class FilmKarakterleriMod {
    public static final String MODID = "filmkarakterlerimod";

    public FilmKarakterleriMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Kayıt işlemlerini buraya bağladık
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChatReplikHandler());
    }
}
