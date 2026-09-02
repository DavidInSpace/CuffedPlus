package git.david.cuffedplus.client;


import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class Keybindings {
    // In some physical client only class
    public static final Keybindings INSTANCE = new Keybindings();

    private Keybindings() {}

    private static final String CATEGORY = "key.categories.cuffedplus.cuffedplus";

    public final KeyMapping openConfigKey = new KeyMapping(
            "key.cuffedplus.config_menu.open",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_O, -1),
            CATEGORY
    );

    public final KeyMapping testKey = new KeyMapping(
            "key.cuffedplus.config_menu.test",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_V, -1),
            CATEGORY
    );

}
