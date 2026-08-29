package git.david.cuffedplus.screen;

import git.david.cuffedplus.CuffedPlusMain;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;


public class ConfigScreen extends Screen implements GuiEventListener {

    private final int CYCLE_BUTTON_X = 20;
    private final int CYCLE_BUTTON_Y = 20;
    private final int CYCLE_BUTTON_HEIGHT = 20;
    private final int CYCLE_BUTTON_WIDTH = 200;

    public ConfigScreen() {
        super(Component.literal("Cuffed Plus Config Screen"));
    }

    private int row(int row) {
        System.out.println("ROW: " + row + " CALC: " + (row * width + CYCLE_BUTTON_X));
        return row * CYCLE_BUTTON_HEIGHT + CYCLE_BUTTON_X;
    }

    private int column(int column) {
        System.out.println("COLUMN: " + column + " CALC: " + (column * height + CYCLE_BUTTON_Y));
        return column * CYCLE_BUTTON_HEIGHT + CYCLE_BUTTON_Y;
    }


    private Component getBooleanOptionsTooltip(String trueText, String falseText) {
        Component falseDescComponent = Component.literal(falseText).withStyle(ChatFormatting.RED);
        Component trueDescComponent = Component.literal(trueText).withStyle(ChatFormatting.GREEN);
        Component trueState = Component.literal(trueText).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD);
        Component falseState = Component.literal(trueText).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD);
        return trueState.copy().append(trueDescComponent.copy()).append("\n").append(falseState.copy()).append(falseDescComponent.copy());
    }

    @Override
    protected void init() {
        super.init();
        LinearLayout configsNavBar = new ConfigNavigationBar(0, 0, width, height);
        LinearLayout configLayout = new LinearLayout(0, configsNavBar.getHeight(), width, height - configsNavBar.getHeight(), LinearLayout.Orientation.VERTICAL);
        //LinearLayout layout = new LinearLayout(width, height, LinearLayout.Orientation.VERTICAL);
        System.out.println("ConfigScreen.init");

        configLayout.defaultChildLayoutSetting().padding(20);

        // Add widgets and precomputed values
        /*List list = new ArrayList();
        List altList = new ArrayList();
        OptionInstance<Boolean> bool = OptionInstance.createBoolean("Test Bool", true);
        OptionInstance.CaptionBasedToString<OptionEnum> optionInstance1 = OptionInstance.forOptionEnum();
        OptionInstance valueSet = new OptionInstance.AltEnum(list, altList, bool);
        OptionInstance optionInstance = new OptionInstance("Test Caption String", Tooltip.create(Component.literal("Test Tooltip")), optionInstance1); */
        //CycleButton<Object> cycleButton = new CycleButton.Builder<>(o -> Component.nullToEmpty(String.valueOf(o))).create(150, 150, 100, 255, Component.literal("pMessage :D"));
        Collection<String> collection = new ArrayList<>();
        CycleButton.ValueListSupplier<String> values = CycleButton.ValueListSupplier.create(collection);

        CycleButton<Boolean> keepLockedGearOnDeath = CycleButton.<Boolean>builder(b -> b ? Component.literal("true") : Component.literal("false")).withValues(true, false).withInitialValue(CuffedPlusMain.SERVER_CONFIG.keepLockedGearOnDeath()).create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Keep Locked Gear On Death"));
        CycleButton<String> playerAttackBehavior = CycleButton.<String>builder(Component::literal).withValues("none", "onlyPrisoners", "onlyOfficers", "both").withInitialValue(CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior()).create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Player Attack Behavior"));
        CycleButton<String> playerAttackBehavior1 = CycleButton.<String>builder(Component::literal).withValues("none", "onlyPrisoners", "onlyOfficers", "both").withInitialValue(CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior()).create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Player Attack Behavior"));
        CycleButton<String> playerAttackBehavior2 = CycleButton.<String>builder(Component::literal).withValues("none", "onlyPrisoners", "onlyOfficers", "both").withInitialValue(CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior()).create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Player Attack Behavior"));
        CycleButton<String> playerAttackBehavior3 = CycleButton.<String>builder(Component::literal).withValues("none", "onlyPrisoners", "onlyOfficers", "both").withInitialValue(CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior()).create(0, 0, CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Player Attack Behavior"));
        keepLockedGearOnDeath.setTooltip(Tooltip.create(getBooleanOptionsTooltip("Ankle monitors and prison jumpsuits wont drop on death if they are locked", "Ankle monitors and prison jumpsuits will drop on death even they are locked")));
        configLayout.addChild(keepLockedGearOnDeath);
        configLayout.addChild(playerAttackBehavior);
        configLayout.addChild(playerAttackBehavior1);
        configLayout.addChild(playerAttackBehavior2);
        configLayout.addChild(playerAttackBehavior3);


        //layout.addChild(configsNavBar);
        //layout.addChild(configLayout);
        configsNavBar.visitWidgets(this::addRenderableWidget);
        configLayout.visitWidgets(this::addRenderableWidget);
        //layout.visitWidgets(this::addRenderableWidget);


        configsNavBar.arrangeElements();
        configLayout.arrangeElements();
        //layout.arrangeElements();
    }


    // In some Screen subclass
    @Override
    public void tick() {
        super.tick();
        // Add ticking logic for EditBox in editBox
        //this.editBox.tick();
    }

    // In some Screen subclass

    // mouseX and mouseY indicate the scaled coordinates of where the cursor is in on the screen
    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Background is typically rendered first
        this.renderBackground(graphics);

        // Render things here before widgets (background textures)

        // Then the widgets if this is a direct child of the Screen
        super.render(graphics, mouseX, mouseY, partialTick);

        // Render things after widgets (tooltips)
    }
}
