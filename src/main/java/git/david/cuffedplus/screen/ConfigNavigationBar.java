package git.david.cuffedplus.screen;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.constants.Styles;
import git.david.cuffedplus.screen.config.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

public class ConfigNavigationBar extends LinearLayout {
    final static byte CATEGORIES_AMOUNT = 5;
    final static int BUTTON_HEIGHT = 20;
    final static int SPACE = 10 * CATEGORIES_AMOUNT;
    private final static Logger LOGGER = LogUtils.getLogger();
    private static String pressedButton = "";

    public ConfigNavigationBar(int x, int y, int width, int height) {
        super(x + 15, y, width - 15, height / 10, Orientation.HORIZONTAL);
        this.defaultChildLayoutSetting().padding(30, 10, 30, 10);
        int buttonWidth = width / CATEGORIES_AMOUNT - SPACE;
        this.addChild(Button.builder(Component.literal("General Settings").withStyle(ChatFormatting.BOLD), b -> {
            Minecraft.getInstance().setScreen(new GeneralConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Role Settings").withStyle(ChatFormatting.BOLD), b -> {
            Minecraft.getInstance().setScreen(new RolesConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Player Settings").withStyle(ChatFormatting.BOLD), b -> {
            Minecraft.getInstance().setScreen(new PlayerConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Prisoner Settings").setStyle(Styles.getPrisonStyle(true)), b -> {
            Minecraft.getInstance().setScreen(new PrisonerConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Officer Settings").setStyle(Styles.getOfficerStyle(true)), b -> {
            Minecraft.getInstance().setScreen(new PrisonerConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Misc Settings").withStyle(ChatFormatting.BOLD), b -> {
            Minecraft.getInstance().setScreen(new MiscConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.arrangeElements();
    }


}
