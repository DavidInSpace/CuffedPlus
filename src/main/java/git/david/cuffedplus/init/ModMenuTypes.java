package git.david.cuffedplus.init;


import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.menu.CuffTableMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CuffedPlusMain.MODID);

    public static final RegistryObject<MenuType<CuffTableMenu>> CUFF_TABLE_MENU = MENU_TYPES.register("cuff_table_menu",
            () -> IForgeMenuType.create(CuffTableMenu::new));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

}
