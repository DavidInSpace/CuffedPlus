package git.david.cuffedplus.logic;

import git.david.cuffedplus.CuffedPlusMain;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class RolesLogic {


    // TODO: Figure out how to make so the role colors are taken from the config
    public static Component getFormattedName(Player player, Component originalName) {
        MutableComponent prefix;
        if (CuffedPlusMain.SERVER_CONFIG.showRolePrefixes()) {
            if (player.getTags().contains("prisoner")) {
                prefix = Component.literal(CuffedPlusMain.SERVER_CONFIG.getPrisonerRolePrefix() + " ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff8800)).withBold(CuffedPlusMain.SERVER_CONFIG.rolePrefixesBold()));
                return prefix.append(originalName);
            } else if (player.getTags().contains("officer")) {
                prefix = Component.literal(CuffedPlusMain.SERVER_CONFIG.getOfficerRolePrefix() + " ").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x5050ff)).withBold(CuffedPlusMain.SERVER_CONFIG.rolePrefixesBold()));
                return prefix.append(originalName);
            }
        }
        return originalName;
    }


    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        Player player = event.getEntity();
        Component originalName = event.getDisplayname();
        Component newName = getFormattedName(player, originalName);
        event.setDisplayname(newName);
    }
}
