package git.david.cuffedplus.screen;

import git.david.cuffedplus.CuffedPlusMain;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;


public class ConfigScreen extends Screen implements GuiEventListener {

    private final int CYCLE_BUTTON_X = 20;
    private final int CYCLE_BUTTON_Y = 20;
    private final int CYCLE_BUTTON_HEIGHT = 20;
    private final int CYCLE_BUTTON_WIDTH = 200;

    public ConfigScreen(Component pTitle) {
        super(pTitle);
    }

    private int row(int row) {
        return row * width + CYCLE_BUTTON_X;
    }

    private int column(int column) {
        return column * height + CYCLE_BUTTON_Y;
    }


    private Component getBooleanOptionsTooltip(String trueText, String falseText) {
        Component falseComponent = Component.literal(falseText).withStyle(ChatFormatting.RED);
        Component trueComponent = Component.literal(trueText).withStyle(ChatFormatting.GREEN);
        return Component.literal("True: ").setStyle(Style.EMPTY).append(trueComponent.copy().withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GREEN).append("\n").append(Component.literal("False: ")).append(falseComponent.copy()).withStyle(ChatFormatting.RED);
    }


    @Override
    protected void init() {
        System.out.println("ConfigScreen.init");
        super.init();
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
        CycleButton<Boolean> keepLockedGearOnDeath = CycleButton.<Boolean>builder(b -> b ? Component.literal("true") : Component.literal("false")).withValues(true, false).withInitialValue(CuffedPlusMain.SERVER_CONFIG.keepLockedGearOnDeath()).create(row(0), column(0), CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Keep Locked Gear On Death"));
        CycleButton<String> playerAttackBehavior = CycleButton.<String>builder(Component::literal).withValues("none", "onlyPrisoners", "onlyOfficers", "both").withInitialValue(CuffedPlusMain.SERVER_CONFIG.getPlayersAttackBehavior()).create(row(0), column(1), CYCLE_BUTTON_WIDTH, CYCLE_BUTTON_HEIGHT, Component.literal("Player Attack Behavior"));
        keepLockedGearOnDeath.setTooltip(Tooltip.create(getBooleanOptionsTooltip("Ankle monitors and prison jumpsuits wont drop on death if they are locked", "Ankle monitors and prison jumpsuits will drop on death even they are locked")));
        this.addRenderableWidget(keepLockedGearOnDeath);
        this.addRenderableWidget(playerAttackBehavior);
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
