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
    final static int SPACE = 10;
    private final static Logger LOGGER = LogUtils.getLogger();
    private static String pressedButton = "";

    public ConfigNavigationBar(int x, int y, int width, int height) {
        super(x, y, width, height / 8, Orientation.HORIZONTAL);
        this.defaultChildLayoutSetting().padding(20, 10, 20, 10);
        int buttonWidth = width / CATEGORIES_AMOUNT - SPACE;
        this.addChild(Button.builder(Component.literal("General Settings").withStyle(ChatFormatting.BOLD), b -> {
            pressedButton = "General Settings";
            Minecraft.getInstance().setScreen(new GeneralConfigScreen());

            if (pressedButton.equals("General Settings")) {
                b.active = true;
            }
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Role Settings").withStyle(ChatFormatting.BOLD), b -> {
            pressedButton = "Role Settings";
            Minecraft.getInstance().setScreen(new RolesConfigScreen());

            if (pressedButton.equals("Role Settings")) {
                b.active = true;
            }
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Player Settings").withStyle(ChatFormatting.BOLD), b -> {
            pressedButton = "Player Settings";
            Minecraft.getInstance().setScreen(new PlayerConfigScreen());

            if (pressedButton.equals("Player Settings")) {
                b.active = true;
            }
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Prisoner Settings").setStyle(Styles.getPrisonStyle(true)), b -> {
            pressedButton = "Prisoner Settings";
            Minecraft.getInstance().setScreen(new PrisonerConfigScreen());

            if (pressedButton.equals("Prisoner Settings")) {
                b.active = true;
            }

        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Misc Settings").withStyle(ChatFormatting.BOLD), b -> {
            Minecraft.getInstance().setScreen(new MiscConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.arrangeElements();
    }


}
