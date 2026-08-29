package git.david.cuffedplus.screen.base;

import git.david.cuffedplus.screen.ConfigNavigationBar;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractConfigScreen extends Screen {

    public boolean isActive = false;
    public static ConfigNavigationBar configNavigationBar;


    protected AbstractConfigScreen(Component pTitle) {
        super(pTitle);
        configNavigationBar = new ConfigNavigationBar(0, 0, this.width, this.height);
    }

    public abstract void init();


    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Background is typically rendered first
        this.renderBackground(graphics);

        // Render things here before widgets (background textures)

        // Then the widgets if this is a direct child of the Screen
        super.render(graphics, mouseX, mouseY, partialTick);


    }
}
