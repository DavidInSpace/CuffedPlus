package git.david.cuffedplus.logic;

import com.lazrproductions.cuffed.CuffedMod;
import com.mojang.blaze3d.platform.InputConstants;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.items.item.base.AnkleMonitorItem;
import git.david.cuffedplus.items.item.base.JumpsuitItem;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LockLogic {
    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    ItemStack hoveringItem;
    int hoveringSlot;

    @SubscribeEvent
    public void onPlayerInteraction(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemInHand = event.getEntity().getItemInHand(event.getHand());
        if (itemInHand.getItem() instanceof ArmorItem && (!(itemInHand.getItem() instanceof JumpsuitItem) || !(itemInHand.getItem() instanceof AnkleMonitorItem)) && itemInHand.getOrCreateTag().getBoolean("CanBeLocked") && itemInHand.getOrCreateTag().getBoolean("Locked")) {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void containerEvent(ContainerScreenEvent event) {
        Player player = Minecraft.getInstance().player;
        Slot slotUnderMouse = event.getContainerScreen().getSlotUnderMouse();
        if (player != null) {
            if (slotUnderMouse != null) {
                hoveringSlot = slotUnderMouse.getSlotIndex();
                hoveringItem = slotUnderMouse.getItem();
            }
        }
    }

    public void DebugInputEvent(InputEvent.MouseButton.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            if (event.getAction() == InputConstants.PRESS) {
                if (event.getButton() == 0) {
                    player.displayClientMessage(Component.literal(event.getButton() + " | Left Click Pressed").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.UNDERLINE), false);
                } else if (event.getButton() == 1) {
                    player.displayClientMessage(Component.literal(event.getButton() + " | Right Click Pressed").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.UNDERLINE), false);
                } else if (event.getButton() == 2) {
                    player.displayClientMessage(Component.literal(event.getButton() + " | Middle Click Pressed").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.UNDERLINE), false);
                }
            } else if (event.getAction() == InputConstants.RELEASE) {
                if (event.getButton() == 0) {
                    player.displayClientMessage(Component.literal(event.getButton() + " | Left Click Released").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.UNDERLINE), false);
                } else if (event.getButton() == 1) {
                    player.displayClientMessage(Component.literal(event.getButton() + " | Right Click Released").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.UNDERLINE), false);
                } else if (event.getButton() == 2) {
                    player.displayClientMessage(Component.literal(event.getButton() + " | Middle Click Released").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.UNDERLINE), false);
                }
            }
        }
    }


    @SubscribeEvent
    public void jumpsuitLockInputEvent(InputEvent.MouseButton.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (minecraft.screen != null && (event.getButton() == 0 || event.getButton() == 1) && player != null) {
            if ((minecraft.screen instanceof InventoryScreen || minecraft.screen instanceof CreativeModeInventoryScreen) && hoveringItem != null && hoveringItem.getItem() instanceof JumpsuitItem && hoveringSlot == 38 && hoveringItem.getOrCreateTag().getBoolean("CanBeLocked") && hoveringItem.getOrCreateTag().getBoolean("Locked")) {
                // GeneralUtils.displayClientMessage(player, String.valueOf(minecraft.screen) + hoveringItem + " " + hoveringItem.getItem() + " " + hoveringSlot + hoveringItem.getOrCreateTag().getBoolean("CanBeLocked") + " " + hoveringItem.getOrCreateTag().getBoolean("Locked"), ChatFormatting.WHITE);
                player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("🔒 Your jumpsuit is locked!  You can not take it off 🔒").withStyle(ChatFormatting.RED), true);
                event.setCanceled(true);
            }
        }
    }


    @SubscribeEvent
    public void ankleMonitorLockInputEvent(InputEvent.MouseButton.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (minecraft.screen != null && (event.getButton() == 0 || event.getButton() == 1) && player != null) {
            if ((minecraft.screen instanceof InventoryScreen || minecraft.screen instanceof CreativeModeInventoryScreen) && hoveringItem != null && hoveringItem.getItem() instanceof AnkleMonitorItem && hoveringSlot == 36 && hoveringItem.getOrCreateTag().getBoolean("CanBeLocked") && hoveringItem.getOrCreateTag().getBoolean("Locked")) {
                // GeneralUtils.displayClientMessage(player, String.valueOf(minecraft.screen) + hoveringItem + " " + hoveringItem.getItem() + " " + hoveringSlot + hoveringItem.getOrCreateTag().getBoolean("CanBeLocked") + " " + hoveringItem.getOrCreateTag().getBoolean("Locked"), ChatFormatting.WHITE);
                player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("🔒 Your ankle monitor is locked!  You can not take it off 🔒").withStyle(ChatFormatting.RED), true);
                event.setCanceled(true);
            }
        }
    }


}
