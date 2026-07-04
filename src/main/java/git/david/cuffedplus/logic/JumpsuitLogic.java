package git.david.cuffedplus.logic;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.items.item.JumpsuitItem;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class JumpsuitLogic {
    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    ItemStack hoveringItem;
    int hoveringSlot;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
            ItemStack itemInChest = event.player.getItemBySlot(EquipmentSlot.CHEST);
            if (itemInChest.getItem() instanceof JumpsuitItem && itemInChest.getOrCreateTag().getBoolean("HighVisibility")) {;
                    event.player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 0, false, false));
            }
    }

    @SubscribeEvent
    public void onPlayerInteraction(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemInHand = event.getEntity().getItemInHand(event.getHand());
        if (itemInHand.getItem() instanceof ArmorItem && !(itemInHand.getItem() instanceof JumpsuitItem) && itemInHand.getOrCreateTag().getBoolean("CanBeLocked") && itemInHand.getOrCreateTag().getBoolean("Locked")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void containerEvent(ContainerScreenEvent event) {
        Player player = Minecraft.getInstance().player;
        Slot slotUnderMouse = event.getContainerScreen().getSlotUnderMouse();
        if (player != null) {
            if (slotUnderMouse  != null){
                hoveringSlot = slotUnderMouse.getSlotIndex();
                hoveringItem = slotUnderMouse.getItem();
            }
        }
    }

    @SubscribeEvent
    public void inputEvent(InputEvent.MouseButton.Pre event) {
        Player player = Minecraft.getInstance().player;
        if ((event.getButton() == 0 || event.getButton() == 1) && player != null) {
            if (hoveringItem != null && hoveringItem.getItem() instanceof JumpsuitItem && hoveringSlot == 38 && hoveringItem.getOrCreateTag().getBoolean("CanBeLocked") && hoveringItem.getOrCreateTag().getBoolean("Locked")) {
                GeneralUtils.displayClientMessage(player, String.valueOf(hoveringItem) + " " + hoveringItem.getItem() + " " + hoveringSlot + hoveringItem.getOrCreateTag().getBoolean("CanBeLocked") + " " + hoveringItem.getOrCreateTag().getBoolean("Locked"), ChatFormatting.WHITE);
                player.playSound(SoundEvents.CHAIN_FALL, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("Your jumpsuit is locked!  You can not take it off").withStyle(ChatFormatting.RED), true);
                event.setCanceled(true);
            }
        }
    }

}
