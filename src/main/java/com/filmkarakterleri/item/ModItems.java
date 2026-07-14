package com.filmkarakterleri.item;

import com.filmkarakterleri.FilmKarakterleriMod;
import com.filmkarakterleri.entity.KarakterData;
import com.filmkarakterleri.entity.ModEntities;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Her karakter için ayrı bir "çağırma taşı" (spawn item) tanımlar.
 * Bu item'a sağ tıklayınca ilgili karaktere ait entity dünyaya spawn olur.
 * (Vanilla spawn egg yerine, mods.toml/lang ile isimlendirmesi daha kolay
 * olsun diye basit bir custom item kullanıyoruz.)
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FilmKarakterleriMod.MODID);

    public static final Map<KarakterData, RegistryObject<Item>> KARAKTER_CAGIRICILARI = new HashMap<>();

    static {
        for (KarakterData karakter : KarakterData.values()) {
            KARAKTER_CAGIRICILARI.put(karakter, ITEMS.register(
                    "cagir_" + karakter.getId(),
                    () -> new KarakterCagiriciItem(karakter, new Item.Properties())
            ));
        }
    }
}
