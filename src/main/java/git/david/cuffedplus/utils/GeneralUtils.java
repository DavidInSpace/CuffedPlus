package git.david.cuffedplus.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class GeneralUtils {

    public static void displayClientMessage(Player player, String text, ChatFormatting color) {
        color = color != null ? color : ChatFormatting.WHITE;
        player.displayClientMessage(Component.literal(text).withStyle(color), false);
    }

    static final String regex1 = "literal{";
    static final String regex2 = "}";
    public static String extractPlayerName(String Name) {
        return Name.replace(regex1, "").replace(regex2, "");
    }

}
