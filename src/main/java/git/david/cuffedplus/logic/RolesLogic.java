package git.david.cuffedplus.logic;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RolesLogic {
    static ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;



    // TODO: Figure out how to make so the role colors are taken from the config
    public static Component getFormattedName(Player player, Component originalName) {
        MutableComponent prefix;
        if (player.getTags().contains("prisoner")) {
            prefix = Component.literal(config.getPrisonerRolePrefix() + " ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff8800)).withBold(true));
            return prefix.append(originalName);
        } else if (player.getTags().contains("officer")) {
            prefix = Component.literal(config.getOfficerRolePrefix() + " ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x5050ff)).withBold(true));
            return prefix.append(originalName);
        } else {
            return originalName;
        }


                /* prefix = Component.literal("[D-CLASS] ")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff6a00)).withBold(true)); */


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
