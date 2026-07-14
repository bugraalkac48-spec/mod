package com.filmkarakterleri;

import com.filmkarakterleri.entity.ModEntities;
import com.filmkarakterleri.event.ChatReplikHandler;
import com.filmkarakterleri.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Film Karakterleri Modu - Ana giriş noktası.
 *
 * Bu mod, 10 farklı "film karakteri" tipinde evcilleştirilebilir bir varlık (entity)
 * ekler. Her karakterin kendi ismi, texture'ı ve chat repliği listesi vardır.
 *
 * NOT: Gerçek karakter isimleri/görselleri/replikleri telif hakkı taşıyabileceğinden
 * bu iskelette yer tutucu (placeholder) değerler kullanılmıştır. Kendi içeriğinle
 * KarakterData.java dosyasını doldurman gerekir.
 */
@Mod(FilmKarakterleriMod.MODID)
public class FilmKarakterleriMod {

    public static final String MODID = "filmkarakterleri";

    public FilmKarakterleriMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        // Chat üzerinden repliklerin tetiklenmesi için event handler'ı kaydet
        MinecraftForge.EVENT_BUS.register(new ChatReplikHandler());
    }
}
