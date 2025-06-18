package me.lojosho.hibiscuscommons.nms;

import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public interface NMSUtils {

    int getNextEntityId();

    /**
     * @deprecated Use {@link NMSHandler#getEntity(int)}
     */
    @Deprecated
    default Entity getEntity(int entityId) {
        return NMSHandlers.getHandler().getEntity(entityId);
    }

    default @Nullable Color getColor(ItemStack itemStack) {
        if (itemStack.getItemMeta() instanceof LeatherArmorMeta meta) {
            return meta.getColor();
        } else {
            return null;
        }
    }

    default ItemStack setColor(@NotNull ItemStack itemStack, Color color) {
        if (itemStack.getItemMeta() instanceof LeatherArmorMeta meta) {
            meta.setColor(color);
            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }

    int getInventoryId(Player bukkitPlayer);

    int incrementInventoryStateId(Player bukkitPlayer);
}
