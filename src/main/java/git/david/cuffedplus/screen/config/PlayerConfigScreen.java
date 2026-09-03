package git.david.cuffedplus.screen.config;

import git.david.cuffedplus.config.base.ConfigOption;
import git.david.cuffedplus.screen.base.AbstractConfigScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import static git.david.cuffedplus.config.Config.PLAYERS_OPTIONS;

public class PlayerConfigScreen extends AbstractConfigScreen {

    private static final Component SCREEN_TITLE = Component.literal("Player Config Screen");

    public PlayerConfigScreen() {
        super(SCREEN_TITLE);
    }


    @Override public void init() {
        super.init();

        // Add info button
        this.addRenderableWidget(
                Button.builder(Component.literal("i").withStyle(ChatFormatting.BOLD), b -> {
                        })
                        .bounds(INFO_BUTTON_X, INFO_BUTTON_Y, INFO_BUTTON_WIDTH, INFO_BUTTON_HEIGHT)
                        .tooltip(Tooltip.create(Component.literal("The underlined option in tooltips is the default option \n\n Here you can change all kinds of things about cuffed+")))
                        .build());

        // Create main layout
        LinearLayout layout = new LinearLayout(0, height / 10, width, height - height / 10, LinearLayout.Orientation.HORIZONTAL);

        // Create columns for the buttons
        LinearLayout configLayoutCol1 = new LinearLayout(getColXPos(0, width), configNavigationBar.getHeight(), width / COL_AMOUNT, height - configNavigationBar.getHeight(), LinearLayout.Orientation.VERTICAL);
        LinearLayout configLayoutCol2 = new LinearLayout(getColXPos(1, width), configNavigationBar.getHeight(), width / COL_AMOUNT, height - configNavigationBar.getHeight(), LinearLayout.Orientation.VERTICAL);
        LinearLayout configLayoutCol3 = new LinearLayout(getColXPos(2, width), configNavigationBar.getHeight(), width / COL_AMOUNT, height - configNavigationBar.getHeight(), LinearLayout.Orientation.VERTICAL);
        LinearLayout configLayoutCol4 = new LinearLayout(getColXPos(3, width), configNavigationBar.getHeight(), width / COL_AMOUNT, height - configNavigationBar.getHeight(), LinearLayout.Orientation.VERTICAL);
        LinearLayout configLayoutCol5 = new LinearLayout(getColXPos(4, width), configNavigationBar.getHeight(), width / COL_AMOUNT, height - configNavigationBar.getHeight(), LinearLayout.Orientation.VERTICAL);

        configLayoutCol1.defaultChildLayoutSetting().padding(10);
        configLayoutCol2.defaultChildLayoutSetting().padding(10);
        configLayoutCol3.defaultChildLayoutSetting().padding(10);
        configLayoutCol4.defaultChildLayoutSetting().padding(10);
        configLayoutCol5.defaultChildLayoutSetting().padding(10);

        // Add the navigation bar
        configNavigationBar.visitWidgets(this::addRenderableWidget);

        // Add buttons to all the columns
        CycleButton<?> cycleButton;
        int MAX_BUTTONS_IN_COLUMN = 10;
        int count = 0;
        for (ConfigOption option : PLAYERS_OPTIONS) {

            count++;
            if (Boolean.parseBoolean(option.getValue(0).getString()))
                cycleButton = createCycleButton(Component.literal(option.getName()), "boolean", option.getID());
            else
                cycleButton = createCycleButton(Component.literal(option.getName()), "string", option.getID());

            if (count < MAX_BUTTONS_IN_COLUMN) {
                configLayoutCol1.addChild(cycleButton);
            } else if (count < MAX_BUTTONS_IN_COLUMN * 2) {
                configLayoutCol2.addChild(cycleButton);
            } else if (count < MAX_BUTTONS_IN_COLUMN * 3) {
                configLayoutCol3.addChild(cycleButton);
            } else if (count < MAX_BUTTONS_IN_COLUMN * 4) {
                configLayoutCol4.addChild(cycleButton);
            } else if (count < MAX_BUTTONS_IN_COLUMN * 5) {
                configLayoutCol5.addChild(cycleButton);
            }
        }

        // Add the column layouts with button to the main layout
        layout.addChild(configLayoutCol1);
        layout.addChild(configLayoutCol2);
        layout.addChild(configLayoutCol3);
        layout.addChild(configLayoutCol4);
        layout.addChild(configLayoutCol5);

        // Add everything to the screen
        configLayoutCol1.visitWidgets(this::addRenderableWidget);
        configLayoutCol2.visitWidgets(this::addRenderableWidget);
        configLayoutCol3.visitWidgets(this::addRenderableWidget);
        configLayoutCol4.visitWidgets(this::addRenderableWidget);
        configLayoutCol5.visitWidgets(this::addRenderableWidget);
        layout.visitWidgets(this::addRenderableWidget);

        configLayoutCol1.arrangeElements();
        configLayoutCol2.arrangeElements();
        configLayoutCol3.arrangeElements();
        configLayoutCol4.arrangeElements();
        configLayoutCol5.arrangeElements();
        layout.arrangeElements();
    }

    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
