package com.filmkarakterleri.entity;

import com.filmkarakterleri.FilmKarakterleriMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FilmKarakterleriMod.MODID);

    public static final RegistryObject<EntityType<FilmKarakteriEntity>> FILM_KARAKTERI =
            ENTITY_TYPES.register("film_karakteri",
                    () -> EntityType.Builder.of(FilmKarakteriEntity::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F)
                            .clientTrackingRange(10)
                            .build("film_karakteri"));
}
