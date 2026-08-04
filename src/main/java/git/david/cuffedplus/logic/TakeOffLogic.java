package git.david.cuffedplus.logic;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.items.item.AnkleMonitorItem;
import git.david.cuffedplus.items.item.JumpsuitItem;
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
    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    // Logic for taking jumpsuits off yourself
    @SubscribeEvent
    public void jumpsuitTakeOffRightClickEmptyEvent(PlayerInteractEvent.RightClickEmpty event) {
        System.out.println("RIGHT CLICK EMPTY EVENT FIRE");
        Player player = event.getEntity();
        if (!player.isCrouching()) return; // Player must be crouching
        if (!player.getItemInHand(event.getHand()).isEmpty()) return; // Player must have an empty hand

        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!(currentChest.getItem() instanceof JumpsuitItem)) return;

        if (currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) {
            player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
            player.displayClientMessage(Component.literal("🔒 Your jumpsuit is locked!  You can not take it off 🔒").withStyle(ChatFormatting.RED), true);
        } else if (currentChest.getOrCreateTag().getBoolean("CanBeLocked") && !currentChest.getOrCreateTag().getBoolean("Locked")) {
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

        if (config.getOtherPlayersJumpsuitBehavior().equals("onlyPutOn".toLowerCase()) || (config.getOtherPlayersJumpsuitBehavior().equals("none"))) {
            user.displayClientMessage(Component.literal("× You can not put jumpsuits on other players ×").withStyle(ChatFormatting.RED), true);
            return ;
        }

        if (user.getTags().contains("prisoner") && config.getOtherPrisonersJumpsuitBehavior().equals("onlyPutOn".toLowerCase()) || config.getOtherPrisonersJumpsuitBehavior().equals("none")) {
            user.displayClientMessage(Component.literal("× Your are a prisoner!  Prisoners can not take off other players jumpsuit ×").withStyle(ChatFormatting.RED), true);
            return;
        }

        ItemStack suit = target.getItemBySlot(EquipmentSlot.CHEST).copyAndClear();

        suit.setCount(1);
        boolean added = user.getInventory().add(suit);
        target.getItemBySlot(EquipmentSlot.CHEST).setCount(0);
        if (!added) user.drop(targetChest, false);
    }


    // Logic for taking jumpsuits off yourself
    @SubscribeEvent
    public void ankleMonitorTakeOffRightClickEmptyEvent(PlayerInteractEvent.RightClickEmpty event) {
        System.out.println("RIGHT CLICK EMPTY EVENT FIRE");
        Player player = event.getEntity();
        if (!player.isCrouching()) return; // Player must be crouching
        if (!player.getItemInHand(event.getHand()).isEmpty()) return; // Player must have an empty hand

        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!(currentChest.getItem() instanceof AnkleMonitorItem)) return;

        if (currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) {
            player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
            player.displayClientMessage(Component.literal("🔒 Your jumpsuit is locked!  You can not take it off 🔒").withStyle(ChatFormatting.RED), true);
        } else if (currentChest.getOrCreateTag().getBoolean("CanBeLocked") && !currentChest.getOrCreateTag().getBoolean("Locked")) {
            if (!currentChest.isEmpty()) {
                boolean added = player.getInventory().add(currentChest);
                if (!added) player.drop(currentChest, false);
            }
        }
    }


    // Logic for taking jumpsuit off someone
    @SubscribeEvent
    public void ankleMonitorTakeOffEntityInteractEvent(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Player)) return;
        Player user = event.getEntity();
        Player target = (Player) event.getTarget();
        if (user.isCrouching()) return; // User's must not be crouching
        if (!user.getItemInHand(event.getHand()).isEmpty()) return; // User's hand must be empty
        ItemStack targetChest = target.getItemBySlot(EquipmentSlot.CHEST);
        if (!(targetChest.getItem() instanceof AnkleMonitorItem)) return;

        if (targetChest.getOrCreateTag().getBoolean("CanBeLocked") && targetChest.getOrCreateTag().getBoolean("Locked")) {
            user.displayClientMessage(Component.literal("🔒 " + GeneralUtils.extractPlayerName(String.valueOf(target.getName())) + "'s jumpsuit is locked on him! 🔒").withStyle(ChatFormatting.RED), true);
            return;
        }

        if (config.getOtherPlayersAnkleMonitorBehavior().equals("onlyPutOn".toLowerCase()) || (config.getOtherPlayersAnkleMonitorBehavior().equals("none"))) {
            user.displayClientMessage(Component.literal("× You can not put jumpsuits on other players ×").withStyle(ChatFormatting.RED), true);
            return ;
        }

        if (user.getTags().contains("prisoner") && config.getOtherPrisonersJumpsuitBehavior().equals("onlyPutOn".toLowerCase()) || config.getOtherPrisonersJumpsuitBehavior().equals("none")) {
            user.displayClientMessage(Component.literal("× Your are a prisoner!  Prisoners can not take off other players jumpsuit ×").withStyle(ChatFormatting.RED), true);
            return;
        }

        ItemStack suit = target.getItemBySlot(EquipmentSlot.CHEST).copyAndClear();

        suit.setCount(1);
        boolean added = user.getInventory().add(suit);
        target.getItemBySlot(EquipmentSlot.CHEST).setCount(0);
        if (!added) user.drop(targetChest, false);
    }

}
