package git.david.cuffedplus.screen;

import git.david.cuffedplus.screen.base.AbstractConfigScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class GeneralConfigScreen extends AbstractConfigScreen {

    protected GeneralConfigScreen(Component pTitle) {
        super(pTitle);
    }

    @Override public void init() {
        configNavigationBar.visitWidgets(this::addRenderableWidget);


    }

    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
