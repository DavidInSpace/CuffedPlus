package git.david.cuffedplus.misc;

import com.lazrproductions.lazrslib.client.screen.base.ScreenTexture;
import git.david.cuffedplus.CuffedPlusMain;
import net.minecraft.resources.ResourceLocation;

public record Icons() {

    public static final ResourceLocation CUFFED_WIDGETS = new ResourceLocation(CuffedPlusMain.MODID, "textures/gui/widgets.png");
    public static final ScreenTexture CAUTION_TAPE_ICON = new ScreenTexture(CUFFED_WIDGETS, 0, 0, 16, 16, 192, 192);
    public static final ScreenTexture WOOD_CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 16, 0, 16, 16, 192, 192);
    public static final ScreenTexture GOLD_CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 32, 0, 16, 16, 192, 192);
    public static final ScreenTexture EMERALD_CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 48, 0, 16, 16, 192, 192);
    public static final ScreenTexture DIAMOND_CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 64, 0, 16, 16, 192, 192);
    public static final ScreenTexture NETHERITE_CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 80, 0, 16, 16, 192, 192);
    public static final ScreenTexture BEDROCK_CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 96, 0, 16, 16, 192, 192);


}
