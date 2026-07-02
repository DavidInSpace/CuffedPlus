package git.david.cuffedplus.misc;

import git.david.cuffedplus.data.WorldSavedData;
import git.david.cuffedplus.items.item.JumpsuitItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class JumpsuitEvent {

    ItemStack hoveringItem;
    int hoveringSlot;
    boolean canPrisonersTakeJumpsuitsOff;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer() && event.phase == TickEvent.Phase.START) {
            if (event.player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JumpsuitItem) {
                //GeneralUtils.displayClientMessage(event.player, "glowing jumpsuit", ChatFormatting.GREEN);
                event.player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 5, false, false));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteraction(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemInHand = event.getEntity().getItemInHand(event.getHand());
        Player player = event.getEntity();
        if (itemInHand.getItem() instanceof ArmorItem && !(itemInHand.getItem() instanceof JumpsuitItem) && !this.canPrisonersTakeJumpsuitsOff) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void containerEvent(ContainerScreenEvent event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (event.getContainerScreen().getSlotUnderMouse() != null && !this.canPrisonersTakeJumpsuitsOff) {
                hoveringSlot = event.getContainerScreen().getSlotUnderMouse().getSlotIndex();
                hoveringItem = event.getContainerScreen().getSlotUnderMouse().getItem();
            }
        }
    }

    @SubscribeEvent
    public void screenEvent(InputEvent.MouseButton event) {
        Player player = Minecraft.getInstance().player;

        if ((event.getButton() == 0 || event.getButton() == 1) && player != null) {
            if (hoveringItem != null && hoveringItem.getItem() instanceof JumpsuitItem && hoveringSlot == 38 && this.canPrisonersTakeJumpsuitsOff) {
                event.setCanceled(true);
            }
        }
    }

}
