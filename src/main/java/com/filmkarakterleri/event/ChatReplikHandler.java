package com.filmkarakterleri.event;

import com.filmkarakterleri.entity.FilmKarakteriEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

/**
 * Oyuncu chat'e mesaj yazdığında, yakınındaki evcil film karakterlerinden
 * rastgele biri (küçük bir olasılıkla) tepki olarak replik söyler.
 *
 * Ayrıca "!replik" yazarsan yakınındaki TÜM evcil karakterlerin hepsi
 * birden replik söyler (istersen bu davranışı değiştirebilirsin).
 */
public class ChatReplikHandler {

    private static final double TEPKI_YARICAPI = 15.0D; // blok
    private static final double RASTGELE_TEPKI_SANSI = 0.15D; // %15

    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        if (player == null) {
            return;
        }

        String mesaj = event.getMessage().getString();

        List<Entity> yakinlar = player.level().getEntities(player,
                player.getBoundingBox().inflate(TEPKI_YARICAPI));

        boolean komutMu = mesaj.equalsIgnoreCase("!replik");

        for (Entity entity : yakinlar) {
            if (!(entity instanceof FilmKarakteriEntity karakterEntity)) {
                continue;
            }
            if (!karakterEntity.isTame()) {
                continue;
            }

            if (komutMu) {
                // Tüm evcil karakterler sırayla konuşsun
                karakterEntity.soyleRepligi(player);
            } else if (Math.random() < RASTGELE_TEPKI_SANSI) {
                // Oyuncu normal konuşurken rastgele tepki
                karakterEntity.soyleRepligi(player);
            }
        }
    }
}
