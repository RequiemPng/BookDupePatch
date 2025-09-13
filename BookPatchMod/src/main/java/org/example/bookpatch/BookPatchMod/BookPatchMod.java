package org.example.bookpatch.BookPatchMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;

@Mod("bookpatch")
public class BookPatchMod {
    public BookPatchMod() {
        MinecraftForge.EVENT_BUS.register(new BookEventHandler());
    }
}
