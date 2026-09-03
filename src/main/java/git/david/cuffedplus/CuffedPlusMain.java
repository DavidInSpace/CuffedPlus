package git.david.cuffedplus;

import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;
import com.lazrproductions.cuffed.restraints.RestraintAPI;
import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;
import git.david.cuffedplus.client.Keybindings;
import git.david.cuffedplus.command.CuffedPlusCommand;
import git.david.cuffedplus.config.AttackBehavior;
import git.david.cuffedplus.config.Config;
import git.david.cuffedplus.config.base.ConfigDescriptions;
import git.david.cuffedplus.events.LoginEvent;
import git.david.cuffedplus.events.ModClientEvents;
import git.david.cuffedplus.init.*;
import git.david.cuffedplus.logic.*;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/*

    This is the main mod class that every forge mod needs to be able to run. For a Cuffed addon there must be some things implement here.


    FIRSTLY, you need to register your custom restraints in the constructor for your mod, just like any other registry.

    OPTIONALLY, you can make your restraints dispensable by adding the following to the common setup fml event:

        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected ItemStack execute(@Nonnull BlockSource source, @Nonnull ItemStack stack) {
                this.setSuccess(AbstractRestraintItem.dispenseRestraint(source, stack));
                if(this.isSuccess())
                    stack.shrink(1);
                return stack;
            }
        };
        DispenserBlock.registerBehavior(MyModItems.MY_RESTRAINT_ITEM.get(), dispenseitembehavior);

    
    LASTLY, and most vitally, in version 1.3.2, Cuffed has difficulty finding addon's registries. So to ensure Cuffed finds your registries, 
    add the following snippet to a function with the RegisterEvent event:
        
        IForgeRegistry<?> r = event.getForgeRegistry();
        if(r != null && r.getValues().size() > 0 && r.getValues().toArray()[0] instanceof AbstractRestraint) {
            if(r.getRegistryName().getNamespace().equals(MODID))
                RestraintAPI.Registries.register(r);
        }
    
    This hopefully will be fixed in the future.


    Use feel free to use this example mod as a template for your addon creation needs.
    
 */


// TODO: Add police uniforms
// TODO: Make so a text/number/image can be put on the back of prison jumpsuits
// TODO: Add more prison jumpsuits
// TODO: Rework crafting
// TODO: Add shackles of different materials (like cuffs)
// TODO: Slow modifier && Low Jump Modifier

// TODO: BEFORE RELEASE
// TODO: Add descriptions to restraints like the original restraint do have
// TODO: Test whether all restraint modifiers still work

// CUFFED GITHUB REPOSITORY PAGE:
// https://github.com/LazrProductions/cuffed

// CUFFED EXAMPLE ADDON REPOSITORY PAGE
// https://github.com/LazrProductions/cuffed_example_addon

// https://github.com/Geforce132/SecurityCraft
// https://github.com/DaRealTurtyWurty/1.20-Tutorial-Mod
// https://forums.minecraftforge.net/topic/82228-1152-3110-intellij-and-gradlew-forge-hotswap-and-dcevm-tutorial/

@Mod(CuffedPlusMain.MODID)
public class CuffedPlusMain {

    public static final boolean DEBUG = true;

    public static final String MODID = "cuffedplus";
    public static final Logger LOGGER = LogManager.getLogger(CuffedPlusMain.MODID);
    //public static final CuffedPlusServerConfig CuffedPlusMain.SERVER_CONFIG = new CuffedPlusServerConfig(MODID, ModCuffedPlusMain.SERVER_CONFIG.Type.SERVER);

    public CuffedPlusMain(FMLJavaModLoadingContext ctx) {
        LOGGER.info("Cuffed Plus: Running CuffedPlusMain");


        IEventBus modEventBus = ctx.getModEventBus();

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModRestraints.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        ModBlock.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModStatistics.register(modEventBus);
        ModRecipes.SERIALIZERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new KeepLockedGearOnDeathLogic());
        MinecraftForge.EVENT_BUS.register(new RolesLogic());
        MinecraftForge.EVENT_BUS.register(new LockLogic());
        MinecraftForge.EVENT_BUS.register(new ModClientEvents());
        MinecraftForge.EVENT_BUS.register(new TakeOffLogic());
        MinecraftForge.EVENT_BUS.register(new GearModifiersLogic());
        MinecraftForge.EVENT_BUS.register(new AttackBehavior());
        MinecraftForge.EVENT_BUS.register(new LoginEvent());

        modEventBus.addListener(this::onRegister);
        modEventBus.addListener(this::commonSetup);

        Config.RegisterConfig();
        ConfigDescriptions.initDescriptions();
        Config.printAllOptions();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Cuffed Plus: Running commonSetup");

        ModStatistics.setup();

        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected @NotNull ItemStack execute(@Nonnull BlockSource source, @Nonnull ItemStack stack) {
                this.setSuccess(AbstractRestraintItem.dispenseRestraint(source, stack));
                if (this.isSuccess())
                    stack.shrink(1);
                return stack;
            }
        };

        //MinecraftForge.EVENT_BUS.register(new ModServerEvents());

        DispenserBlock.registerBehavior(ModItems.WOOD_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.GOLD_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.DIAMOND_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.NETHERITE_CUFFS.get(), dispenseitembehavior);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Keys.SOUND_EVENTS))
            ModSounds.register(event);

        // Temporary fix for Cuffed not recognizing custom registries
        IForgeRegistry<?> r = event.getForgeRegistry();
        if (r != null && !r.getValues().isEmpty() && r.getValues().toArray()[0] instanceof AbstractRestraint) {
            if (r.getRegistryName().getNamespace().equals(MODID))
                RestraintAPI.Registries.register(r);
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        LOGGER.info("Cuffed Plus: Registering Commands");
        new CuffedPlusCommand(event.getDispatcher(), event.getBuildContext());
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            LOGGER.info("Cuffed Plus: Registering Keys");
            event.register(Keybindings.INSTANCE.openConfigKey);
            event.register(Keybindings.INSTANCE.testKey);
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            LOGGER.info("Cuffed Plus: Registering Layers");
            // Register the model layers for the custom restraint models.
            ModModelLayers.registerLayers(event);
        }
    }

}