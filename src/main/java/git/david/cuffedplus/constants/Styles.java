package git.david.cuffedplus.constants;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class Styles {

    private static final Style prisonStyle = Style.EMPTY.withColor(TextColor.fromRgb(0xff8800));
    private static final Style officerStyle = Style.EMPTY.withColor(TextColor.fromRgb(0x5050ff));

    private static final Style TRUE_BOOL_STYLE = Style.EMPTY.withColor(ChatFormatting.GREEN);
    private static final Style FALSE_BOOL_STYLE = Style.EMPTY.withColor(ChatFormatting.RED);

    private static final Style DARK_GREEN_STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_GREEN);
    private static final Style DARK_RED_STYLE = Style.EMPTY.withColor(ChatFormatting.RED);
    private static final Style WHITE_STYLE = Style.EMPTY.withColor(ChatFormatting.WHITE);


    public static Style getPrisonStyle(boolean bold) {
        return prisonStyle.withBold(bold);
    }

    public static Style getOfficerStyle(boolean bold) {
        return officerStyle.withBold(bold);
    }

    public static Style getTrueStyle(boolean bold) {
        return TRUE_BOOL_STYLE.withBold(bold);
    }

    public static Style getFalseStyle(boolean bold) {
        return FALSE_BOOL_STYLE.withBold(bold);
    }

    public static Style getDarkGreenStyle(boolean bold) {
        return DARK_GREEN_STYLE.withBold(bold);
    }

    public static Style getDarkRedStyle(boolean bold) {
        return DARK_RED_STYLE.withBold(bold);
    }

    public static Style getWhiteStyle(boolean bold) {
        return WHITE_STYLE.withBold(bold);
    }

}
