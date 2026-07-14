package com.filmkarakterleri.item;

import com.filmkarakterleri.entity.FilmKarakteriEntity;
import com.filmkarakterleri.entity.KarakterData;
import com.filmkarakterleri.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * Elde tutulup bir bloğa sağ tıklandığında ilgili karakteri dünyaya spawn eder.
 */
public class KarakterCagiriciItem extends Item {

    private final KarakterData karakter;

    public KarakterCagiriciItem(KarakterData karakter, Properties properties) {
        super(properties);
        this.karakter = karakter;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().above();

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            FilmKarakteriEntity entity = ModEntities.FILM_KARAKTERI.get().create(serverLevel);
            if (entity != null) {
                entity.setKarakterIndex(karakter.ordinal());
                entity.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
                entity.finalizeSpawn(serverLevel, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
                serverLevel.addFreshEntity(entity);

                if (!context.getPlayer().getAbilities().instabuild) {
                    context.getItemInHand().shrink(1);
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
