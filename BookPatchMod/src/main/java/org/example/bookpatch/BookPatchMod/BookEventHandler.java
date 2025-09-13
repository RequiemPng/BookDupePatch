package org.example.bookpatch.BookPatchMod;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BookEventHandler {

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem() == Items.WRITABLE_BOOK || stack.getItem() == Items.WRITTEN_BOOK) {
            if (stack.hasTag() && stack.getTag().contains("pages")) {
                ListNBT pages = stack.getTag().getList("pages", 8); // 8 = StringNBT

                // Limit page count and content length
                if (pages.size() > 50) {
                    pages = new ListNBT();
                    pages.add(StringNBT.valueOf("Too many pages. Book reset."));
                    stack.getTag().put("pages", pages);
                } else {
                    for (int i = 0; i < pages.size(); i++) {
                        String page = pages.getString(i);
                        if (page.length() > 256) {
                            pages.set(i, StringNBT.valueOf("Page too long. Truncated."));
                        }
                    }
                }
            }
        }
    }
}
