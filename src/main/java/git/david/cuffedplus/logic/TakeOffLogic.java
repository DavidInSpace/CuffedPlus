package git.david.cuffedplus.logic;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.items.item.base.AnkleMonitorItem;
import git.david.cuffedplus.items.item.base.JumpsuitItem;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class TakeOffLogic {


    // Logic for taking jumpsuits off yourself
    @SubscribeEvent
    public void jumpsuitTakeOffRightClickEmptyEvent(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        if (!player.isCrouching()) return; // Player must be crouching


        /**
         * For some reason event.getItemStack() or event.getEntity().getItemInHand(event.getHand()); or anything besides event.getEntity().getMainHandItem() doesn't work
         * and just return air even if the player is holding an item
         * */

        if (!event.getEntity().getMainHandItem().isEmpty()) return; // Player must have an empty hand

        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!(currentChest.getItem() instanceof JumpsuitItem)) return;

        if (currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) {
            player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
            player.displayClientMessage(Component.literal("🔒 Your jumpsuit is locked!  You can not take it off 🔒").withStyle(ChatFormatting.RED), true);
        } else if (!currentChest.getOrCreateTag().getBoolean("Locked")) {
            if (!currentChest.isEmpty()) {
                boolean added = player.getInventory().add(currentChest);
                if (!added) player.drop(currentChest, false);
            }
        }
    }


    // Logic for taking jumpsuit off someone
    @SubscribeEvent
    public void jumpsuitTakeOffEntityInteractEvent(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Player)) return;
        Player user = event.getEntity();
        Player target = (Player) event.getTarget();
        if (user.isCrouching()) return; // User's must not be crouching
        if (!user.getItemInHand(event.getHand()).isEmpty()) return; // User's hand must be empty
        ItemStack targetChest = target.getItemBySlot(EquipmentSlot.CHEST);
        if (!(targetChest.getItem() instanceof JumpsuitItem)) return;

        if (targetChest.getOrCreateTag().getBoolean("CanBeLocked") && targetChest.getOrCreateTag().getBoolean("Locked")) {
            user.displayClientMessage(Component.literal("🔒 " + GeneralUtils.extractPlayerName(String.valueOf(target.getName())) + "'s jumpsuit is locked on him! 🔒").withStyle(ChatFormatting.RED), true);
            return;
        }

        if (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersJumpsuitBehavior().equals("onlyPutOn".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersJumpsuitBehavior().equals("none"))) {
            user.displayClientMessage(Component.literal("× You can not take jumpsuits off other players ×").withStyle(ChatFormatting.RED), true);
            return ;
        }

        if (user.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersJumpsuitBehavior().equals("onlyPutOn".toLowerCase()) || CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersJumpsuitBehavior().equals("none")) {
            user.displayClientMessage(Component.literal("× Your are a prisoner!  Prisoners can not take off other players jumpsuit ×").withStyle(ChatFormatting.RED), true);
            return;
        }

        ItemStack suit = target.getItemBySlot(EquipmentSlot.CHEST).copy();
        suit.setCount(1);
        boolean added = user.getInventory().add(suit);
        target.getItemBySlot(EquipmentSlot.CHEST).setCount(0);
        if (!added) user.drop(targetChest, false);
    }


    // Logic for taking ankle monitor off yourself
    @SubscribeEvent
    public void ankleMonitorTakeOffRightClickEmptyEvent(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        if (!player.isCrouching()) return; // Player must be crouching
        if (!event.getEntity().getMainHandItem().isEmpty()) return; // Player must have an empty hand

        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.FEET);

        if (!(currentChest.getItem() instanceof AnkleMonitorItem)) return;

        if (currentChest.getOrCreateTag().getBoolean("Locked")) {
            player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
            player.displayClientMessage(Component.literal("🔒 Your ankle monitor is locked!  You can not take it off 🔒").withStyle(ChatFormatting.RED), true);
        } else if (currentChest.getOrCreateTag().getBoolean("CanBeLocked") && !currentChest.getOrCreateTag().getBoolean("Locked")) {
            if (!currentChest.isEmpty()) {
                boolean added = player.getInventory().add(currentChest);
                if (!added) player.drop(currentChest, false);
            }
        }
    }


    // Logic for taking ankle monitor off someone
    @SubscribeEvent
    public void ankleMonitorTakeOffEntityInteractEvent(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Player)) return;
        Player user = event.getEntity();
        Player target = (Player) event.getTarget();
        if (user.isCrouching()) return; // User's must not be crouching
        if (!user.getItemInHand(event.getHand()).isEmpty()) return; // User's hand must be empty
        ItemStack targetFeet = target.getItemBySlot(EquipmentSlot.FEET);
        if (!(targetFeet.getItem() instanceof AnkleMonitorItem)) return;

        if (targetFeet.getOrCreateTag().getBoolean("CanBeLocked") && targetFeet.getOrCreateTag().getBoolean("Locked")) {
            user.displayClientMessage(Component.literal("🔒 " + GeneralUtils.extractPlayerName(String.valueOf(target.getName())) + "'s ankle monitor is locked on them! 🔒").withStyle(ChatFormatting.RED), true);
            return;
        }

        if (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorBehavior().equals("onlyPutOn".toLowerCase()) || (CuffedPlusMain.SERVER_CONFIG.getOtherPlayersAnkleMonitorBehavior().equals("none"))) {
            user.displayClientMessage(Component.literal("× You can not take ankle monitors off other players ×").withStyle(ChatFormatting.RED), true);
            return ;
        }

        if (user.getTags().contains("prisoner") && CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorBehavior().equals("onlyPutOn".toLowerCase()) || CuffedPlusMain.SERVER_CONFIG.getOtherPrisonersAnkleMonitorBehavior().equals("none")) {
            user.displayClientMessage(Component.literal("× Your are a prisoner!  Prisoners can not take off other players ankle monitor ×").withStyle(ChatFormatting.RED), true);
            return;
        }

        ItemStack monitor = target.getItemBySlot(EquipmentSlot.FEET).copy();
        monitor.setCount(1);
        boolean added = user.getInventory().add(monitor);
        target.getItemBySlot(EquipmentSlot.FEET).setCount(0);
        if (!added) user.drop(targetFeet, false);
    }

}
