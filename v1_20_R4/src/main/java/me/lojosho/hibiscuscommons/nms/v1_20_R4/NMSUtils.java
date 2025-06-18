package me.lojosho.hibiscuscommons.nms.v1_20_R4;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.component.DyedItemColor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NMSUtils implements me.lojosho.hibiscuscommons.nms.NMSUtils {

    @Override
    public int getNextEntityId() {
        return net.minecraft.world.entity.Entity.nextEntityId();
    }

    @Override
    public int getInventoryId(Player bukkitPlayer) {
        ServerPlayer player = ((CraftPlayer) bukkitPlayer).getHandle();
        return player.inventoryMenu.containerId;
    }

    @Override
    public int incrementInventoryStateId(Player bukkitPlayer) {
        ServerPlayer player = ((CraftPlayer) bukkitPlayer).getHandle();
        return player.inventoryMenu.incrementStateId();
    }

    @Override
    public @Nullable Color getColor(ItemStack itemStack) {
        if (itemStack == null) return null;
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        if (nmsItem == null) return null;

        DyedItemColor color = nmsItem.get(DataComponents.DYED_COLOR);
        if (color == null) return null;
        return Color.fromRGB(color.rgb());
    }

    @Override
    public ItemStack setColor(@NotNull ItemStack itemStack, Color color) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        boolean tooltip = !nmsStack.has(DataComponents.DYED_COLOR) || nmsStack.get(DataComponents.DYED_COLOR).showInTooltip();
        nmsStack.set(DataComponents.DYED_COLOR, new DyedItemColor(color.asRGB(), tooltip));
        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
