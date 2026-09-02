package git.david.cuffedplus.init;

import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.items.restraints.custom.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

/*
    This is the registry class that is used to register custom restraints. Your mod must have one of these to register your restraints.
    It works just like any other mod registry, except the register function has some fluff to it to make it work.

    1. CREATE THE REGISTRY
        First you need to create the DefferedRegister for your mod, the one here is called RESTRAINTS. this will create a registry for AbstractRestraints for the path:
            "yourmodid:restraints"

    2. REGISTER RESTRAINTS
        Second you should create your static references to your restraint RegistryObjects like this:

            public static final RegistryObject<AbstractRestraint> MY_RESTRAINT = RESTRAINTS.register("my_restraint" , MyRestraintClass::new);

        This uses that empty constructor in your restraint class to create a registry instance of the restraint to be copied when it is applied to players.

    3. REGISTER THE REGISTRY
        Finally you add the register function to this class to pack the registry for Cuffed to handle it:
        
            public static void register(final IEventBus modEventBus) {
                RESTRAINTS.makeRegistry(RegistryBuilder::new);
                RESTRAINTS.register(modEventBus);
            }
        
        Then call the register function in your mod's main class:
            
            public MyMod() {
                MyRestraintsClass.register(bus);
            }
 */

public class ModRestraints {
    // Create your custom registry to hold your restraint classes
    public static final DeferredRegister<AbstractRestraint> RESTRAINTS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraints"), CuffedPlusMain.MODID);
    // Register your restraints to the register
    // WOOD
    public static final RegistryObject<AbstractRestraint> WOOD_CUFFS_ARMS = RESTRAINTS.register("wood_cuffs_arms", WoodCuffsArmsRestraint::new);
    public static final RegistryObject<AbstractRestraint> WOOD_CUFFS_LEGS = RESTRAINTS.register("wood_cuffs_legs", WoodCuffsLegsRestraint::new);
    // GOLD
    public static final RegistryObject<AbstractRestraint> GOLD_CUFFS_ARMS = RESTRAINTS.register("gold_cuffs_arms", GoldCuffsArmsRestraint::new);
    public static final RegistryObject<AbstractRestraint> GOLD_CUFFS_LEGS = RESTRAINTS.register("gold_cuffs_legs", GoldCuffsLegsRestraint::new);
    // DIAMOND
    public static final RegistryObject<AbstractRestraint> DIAMOND_CUFFS_ARMS = RESTRAINTS.register("diamond_cuffs_arms", DiamondCuffsArmsRestraint::new);
    public static final RegistryObject<AbstractRestraint> DIAMOND_CUFFS_LEGS = RESTRAINTS.register("diamond_cuffs_legs", DiamondCuffsLegsRestraint::new);
    // NETHERITE
    public static final RegistryObject<AbstractRestraint> NETHERITE_CUFFS_ARMS = RESTRAINTS.register("netherite_cuffs_arms", NetheriteCuffsArmsRestraint::new);
    public static final RegistryObject<AbstractRestraint> NETHERITE_CUFFS_LEGS = RESTRAINTS.register("netherite_cuffs_legs", NetheriteCuffsLegsRestraint::new);
    public static final RegistryObject<AbstractRestraint> HAZARD_TAPE_HEAD = RESTRAINTS.register("hazard_tape_head", HazardTapeHeadRestraint::new);
    public static final RegistryObject<AbstractRestraint> HAZARD_TAPE_ARMS = RESTRAINTS.register("hazard_tape_arms", HazardTapeArmsRestraint::new);
    public static final RegistryObject<AbstractRestraint> HAZARD_TAPE_LEGS = RESTRAINTS.register("hazard_tape_legs", HazardTapeLegsRestraint::new);
    public static final RegistryObject<AbstractRestraint> EXAMPLE_HEAD_RESTRAINT = RESTRAINTS.register("example_head_restraint", ExampleHeadRestraint::new);
    // this isn't required, it just can help a bit when developing in case things go awry.
    private static boolean isInitialized = false;

    public static void register(final IEventBus modEventBus) {
        if (isInitialized)
            throw new IllegalStateException("Restraints already initialized"); // Not required, just useful for development.

        RESTRAINTS.makeRegistry(RegistryBuilder::new);
        RESTRAINTS.register(modEventBus);

        // Not required, just useful for development.
        isInitialized = true;
        CuffedPlusMain.LOGGER.info("Registered restraints");
    }
}
