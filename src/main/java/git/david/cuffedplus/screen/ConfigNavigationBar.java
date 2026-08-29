package git.david.cuffedplus.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.network.chat.Component;

public class ConfigNavigationBar extends LinearLayout {

    final static byte CATEGORIES_AMOUNT = 5;
    final static int BUTTON_HEIGHT = 20;
    final static int SPACE = 10;

    public ConfigNavigationBar(int x, int y, int width, int height) {
        super(x, y, width, height / 6, Orientation.HORIZONTAL);
        this.defaultChildLayoutSetting().padding(20, 10, 20, 20);
        int buttonWidth = width / CATEGORIES_AMOUNT - SPACE;
        this.addChild(Button.builder(Component.literal("General Settings"), b -> {
            Minecraft.getInstance().setScreen(new GeneralConfigScreen(Component.literal("General Config Screen")));
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Role Settings"), b -> {
            Minecraft.getInstance().setScreen(new ConfigScreen());
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Player Settings"), b -> {
            Minecraft.getInstance().setScreen(new PlayerConfigScreen(Component.literal("Player Config Screen")));
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Prisoner Settings"), b -> {
            Minecraft.getInstance().setScreen(new PrisonerConfigScreen(Component.literal("Prisoner Config Screen")))
        };).size(buttonWidth, BUTTON_HEIGHT).build());

        this.addChild(Button.builder(Component.literal("Misc Settings"), b -> {
            Minecraft.getInstance().setScreen(new MiscConfigScreen(Component.literal("Misc Config Screen")));
        }).size(buttonWidth, BUTTON_HEIGHT).build());

        this.arrangeElements();
    }


}
