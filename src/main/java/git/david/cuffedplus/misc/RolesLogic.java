package git.david.cuffedplus.misc;

import git.david.cuffedplus.items.item.JumpsuitItem;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RolesLogic {
    public static Component getFormattedName(Player player, Component originalName) {
        MutableComponent prefix;
        if (player.getTags().contains("prisoner")) {
            prefix = Component.literal("[INMATE] ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff8800)).withBold(true));
            return prefix.append(originalName);
        } else if (player.getTags().contains("officer")) {
            prefix = Component.literal("[OFFICER] ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x5050ff)).withBold(true));
            return prefix.append(originalName);
        } else {
            return originalName;
        }


                /* prefix = Component.literal("[D-CLASS] ")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff6a00)).withBold(true)); */


    }



    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        GeneralUtils.displayClientMessage(event.player, "Tick Player", ChatFormatting.WHITE);
        if (event.side.isServer() && event.phase == TickEvent.Phase.START) {
            if (event.player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JumpsuitItem) {

            }
            GeneralUtils.displayClientMessage(event.player, "glowing jumpsuit", ChatFormatting.GREEN);
            event.player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1, 1, false, false));
        }
    }

    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        Player player = event.getEntity();
        player.displayClientMessage(Component.literal("On Name Format"), false);
        Component originalName = event.getDisplayname();
        Component newName = getFormattedName(player, originalName);
        event.setDisplayname(newName);
    }
}
