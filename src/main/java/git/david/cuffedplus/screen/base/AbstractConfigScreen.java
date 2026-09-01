package git.david.cuffedplus.screen.base;

import com.mojang.logging.LogUtils;
import git.david.cuffedplus.config.ConfigSaveData;
import git.david.cuffedplus.config.base.ConfigOption;
import git.david.cuffedplus.config.base.DescriptionHolder;
import git.david.cuffedplus.constants.Styles;
import git.david.cuffedplus.init.ModNetwork;
import git.david.cuffedplus.net.C2SConfigPacket;
import git.david.cuffedplus.screen.ConfigNavigationBar;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Arrays;

import static git.david.cuffedplus.config.Config.getOptionById;
import static git.david.cuffedplus.config.base.ConfigDescriptions.DESCRIPTIONS;

public abstract class AbstractConfigScreen extends Screen {
    private final static Logger LOGGER = LogUtils.getLogger();

    protected final static int COL_AMOUNT = 5;
    private final static int CYCLE_BUTTON_X = 20;
    private final static int CYCLE_BUTTON_Y = 20;
    private final static int CYCLE_BUTTON_HEIGHT = 20;
    private final static int CYCLE_BUTTON_WIDTH = 200;
    public boolean isActive = false;
    protected static ConfigNavigationBar configNavigationBar;
    private static ServerLevel serverLevel;

    public static void setServerLevel(ServerLevel SL) {
        serverLevel = SL;


    }


    private static int WIDTH;


    protected AbstractConfigScreen(Component pTitle) {
        super(pTitle);
        WIDTH = this.width;
        configNavigationBar = new ConfigNavigationBar(0, 0, this.width, this.height);
    }

    @Override
    public void init() {
        super.init();
        configNavigationBar = new ConfigNavigationBar(0, 0, this.width, this.height);
        configNavigationBar.visitWidgets(this::addRenderableWidget);
        configNavigationBar.arrangeElements();
    }


    protected CycleButton<?> createCycleButton(Component name, String type, String id) {
        CycleButton cycleButton;
        ConfigOption configOption = getOptionById(id);
        Component[] options = configOption.getValues();
        String defaultOption;
        ModNetwork.sendToServer(new C2SConfigPacket(id, null, "get"));


        ConfigSaveData data = ConfigSaveData.compute(serverLevel);
        try {
            defaultOption = options[configOption.getDefaultValue()].getString();
        } catch (IndexOutOfBoundsException e) {
            defaultOption = options[0].getString();
        }
        if (type.equalsIgnoreCase("string")) {
            cycleButton = CycleButton.builder(Component::literal)
                    .withValues(Arrays.stream(options).iterator().next().getString()).withInitialValue(data.getOptionByID(id))
                    .create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, name, (btn, str) -> {
                        ModNetwork.sendToServer(new C2SConfigPacket(id, str, "put"));
                        btn.setTooltip(Tooltip.create(Component.literal(configOption.getName() + "\n\n").withStyle(ChatFormatting.BOLD).append(getDescription(id, configOption.getDescriptionNum()))));
                    });
        } else if (type.equalsIgnoreCase("boolean")) {
            cycleButton = CycleButton.booleanBuilder(Component.literal("True").setStyle(Styles.getTrueStyle(true)), Component.literal("False").setStyle(Styles.getFalseStyle(true)))
                    .withInitialValue(Boolean.valueOf(data.getOptionByID(id)))
                    .create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, name, (btn, bool) -> {
                        ModNetwork.sendToServer(new C2SConfigPacket(id, bool.toString(), "put"));
                        btn.setTooltip(Tooltip.create(Component.literal(configOption.getName() + "\n\n").withStyle(ChatFormatting.BOLD).append(getDescription(id, configOption.getDescriptionNum()))));
                    });
        } else {
            return CycleButton.builder(Component::literal)
                    .withValues(Arrays.toString(options)).withInitialValue(defaultOption)
                    .create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("ERROR BUTTON").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
        }
        return cycleButton;
    }

    public static Component getDescription(String id, int number) {
        LOGGER.debug("Config ID: {}   Number: {}", id, number);

        // Minus 1 so the index wont be out of bounce
        if (number > 4) {
            return Component.literal("There can only be 4 values at most").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD);
        } // There can only be 4 descriptions at most
        for (DescriptionHolder descriptionHolder : DESCRIPTIONS) {
            System.out.println("LOOKING FOR: " + id + "  CURRENT: " + descriptionHolder.getID());
            if (descriptionHolder.getID().equals(id)) {
                try {
                    if (number == 1) {
                        return descriptionHolder.descriptions[0].copy();
                    } else if (number == 2) {
                        return descriptionHolder.descriptions[0].copy().append(descriptionHolder.descriptions[1].copy());
                    } else if (number == 3) {
                        return descriptionHolder.descriptions[0].copy().append(descriptionHolder.descriptions[1].copy().append(descriptionHolder.descriptions[2].copy()));
                    } else if (number == 4) {
                        return descriptionHolder.descriptions[0].copy().append(descriptionHolder.descriptions[1].copy().append(descriptionHolder.descriptions[2].copy()).append(descriptionHolder.descriptions[3].copy()));
                    }
                } catch (IndexOutOfBoundsException e) {
                    return Component.literal("INDEX OUT OF BOUNDS ERROR").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD);
                }
            }
        }
        return Component.literal("NO DESCRIPTION FOUND").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD);
    }

    protected static int getColXPos(int col) {
        return ((WIDTH / COL_AMOUNT + 20) * col);
    }


    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Background is typically rendered first
        this.renderBackground(graphics);

        // Render things here before widgets (background textures)

        // Then the widgets if this is a direct child of the Screen
        super.render(graphics, mouseX, mouseY, partialTick);


    }
}
