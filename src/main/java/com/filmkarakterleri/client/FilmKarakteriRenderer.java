package com.filmkarakterleri.client;

import com.filmkarakterleri.entity.FilmKarakteriEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Basit bir humanoid (insan şeklinde) model üzerinden karakteri render eder.
 * Vanilla oyuncu modelini iskelet olarak kullanır; texture her karaktere göre
 * KarakterData'dan çekilir. Kendi 3D modelini eklemek istersen bu sınıfı
 * genişletip özel bir EntityModel kullanabilirsin (Blockbench ile üretilebilir).
 */
public class FilmKarakteriRenderer extends MobRenderer<FilmKarakteriEntity, HumanoidModel<FilmKarakteriEntity>> {

    public FilmKarakteriRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FilmKarakteriEntity entity) {
        return entity.getKarakter().getTexture();
    }

    @Override
    protected void scale(FilmKarakteriEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
