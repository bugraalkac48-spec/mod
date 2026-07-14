package com.filmkarakterleri;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("filmkarakterlerimod")
public class FilmKarakterleriMod {
    public FilmKarakterleriMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
