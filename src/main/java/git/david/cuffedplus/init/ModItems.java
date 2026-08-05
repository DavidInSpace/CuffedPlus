package git.david.cuffedplus.init;

import com.lazrproductions.cuffed.items.base.AbstractRestraintKeyItem;

import git.david.cuffedplus.CuffedPlusMain;


import git.david.cuffedplus.items.item.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Properties;

/*
        Very basic item registry, nothing special to note here :)
 */

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        CuffedPlusMain.MODID);


    // WOOD
    public static final RegistryObject<Item> WOOD_CUFFS_KEY = ITEMS.register("wood_cuffs_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WOOD_CUFFS = ITEMS.register("wood_cuffs",
            () -> new RestraintItem(new Item.Properties().stacksTo(1)
                    .durability(15)
                    .defaultDurability(15)));

    // GOLD
    public static final RegistryObject<Item> GOLD_CUFFS_KEY = ITEMS.register("gold_cuffs_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GOLD_CUFFS = ITEMS.register("gold_cuffs",
            () -> new RestraintItem(new Item.Properties().stacksTo(1)
                    .durability(5)
                    .defaultDurability(5)));

    // EMERALD
    public static final RegistryObject<Item> EMERALD_CUFFS_KEY = ITEMS.register("emerald_cuffs_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> EMERALD_CUFFS = ITEMS.register("emerald_cuffs",
            () -> new RestraintItem(
                    new Item.Properties().stacksTo(1)
                    .durability(40)
                    .defaultDurability(40)));


    // DIAMOND
    public static final RegistryObject<Item> DIAMOND_CUFFS_KEY = ITEMS.register("diamond_cuffs_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DIAMOND_CUFFS = ITEMS.register("diamond_cuffs",
            () -> new RestraintItem(new Item.Properties().stacksTo(1)
                    .durability(60)
                    .defaultDurability(60)));

    // NETHERITE
    public static final RegistryObject<Item> NETHERITE_CUFFS_KEY = ITEMS.register("netherite_cuffs_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant()));


    public static final RegistryObject<Item> NETHERITE_CUFFS = ITEMS.register("netherite_cuffs",
            () -> new RestraintItem(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.UNCOMMON)
                    .durability(80)
                    .defaultDurability(80)
                    .fireResistant()));


    // BEDROCK
    public static final RegistryObject<Item> BEDROCK_CUFFS_KEY = ITEMS.register("bedrock_cuffs_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final RegistryObject<Item> BEDROCK_CUFFS = ITEMS.register("bedrock_cuffs",
            () -> new RestraintItem(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .durability(999999999)
                    .defaultDurability(999999999)
                    .fireResistant()));

    public static final RegistryObject<Item> EXAMPLE_HEAD_RESTRAINT = ITEMS.register("example_head_restraint",
            () -> new ExampleHeadRestraintItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> HAZARD_TAPE = ITEMS.register("hazard_tape",
            () -> new ExampleHeadRestraintItem(new Item.Properties().stacksTo(1)
                    .durability(20)
                    .defaultDurability(20)));


    /* UNIFORMS */

    public static final RegistryObject<Item> DCLASS_JUMPSUIT = ITEMS.register("dclass_jumpsuit",
            () -> new JumpsuitItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PRISON_JUMPSUIT_1 = ITEMS.register("prison_jumpsuit_1",
            () -> new JumpsuitItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PRISON_JUMPSUIT_2 = ITEMS.register("prison_jumpsuit_2",
            () -> new JumpsuitItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PRISON_JUMPSUIT_3 = ITEMS.register("prison_jumpsuit_3",
            () -> new JumpsuitItem(new Item.Properties().stacksTo(16)));


    /* RESTRAINTS MODIFICATORS */

    public static final RegistryObject<Item> SATURATION_MODIFIER = ITEMS.register("saturation_modifier",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> HUNGER_MODIFIER = ITEMS.register("hunger_modifier",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ANTI_GOD_MODIFIER = ITEMS.register("anti_god_modifier",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> JUMP_MODIFIER = ITEMS.register("jump_modifier",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> IS_LOCKPICKABLE_MODIFIER = ITEMS.register("is_lockpickable",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> CAN_BE_BROKEN_OUT_OF = ITEMS.register("can_be_broken_out_of",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> TIMER_MODIFIER = ITEMS.register("timer_modifier",
            () -> new ModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));


    /* JUMPSUIT & Ankle Monitor MODIFICATORS */


    public static final RegistryObject<Item> LOCK_MODIFIER = ITEMS.register("lock_modifier",
            () -> new JumpsuitModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> HIGH_VISIBILITY_MODIFIER = ITEMS.register("high_visibility_modifier",
            () -> new JumpsuitModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));


    // TODO: Finish this modifier. I want the player to emit actual light when wearing something with this modifier on
    public static final RegistryObject<Item> GLOW_MODIFIER = ITEMS.register("glow_modifier",
            () -> new JumpsuitModifierItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));



    public static final RegistryObject<BlockItem> CUFF_TABLE = ITEMS.register("cuff_table",
            () -> new BlockItem(ModBlock.CUFF_TABLE_BLOCK.get(), new Item.Properties()));


    /* ANKLE MONITOR */

    /**    public static final RegistryObject<Item> ANKLE_MONITOR_CLEAN = ITEMS.register("ankle_monitor_clean",
            () -> new RestraintItem(new Item.Properties().stacksTo(4).rarity(Rarity.COMMON)));


    * WILL RETURN TO THAT ONCE I FIGURE OUT HOW TO MAKE A NORMAL ANKLE MONITOR
    * public static final RegistryObject<Item> ANKLE_MONITOR_CLEAN_KEY = ITEMS.register("ankle_monitor_clean_key",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(4).rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> ANKLE_MONITOR_DIRTY = ITEMS.register("ankle_monitor_dirty",
            () -> new AbstractRestraintKeyItem(new Item.Properties().stacksTo(4).rarity(Rarity.COMMON)));
     */


   public static final RegistryObject<Item> ANKLE_MONITOR = ITEMS.register("ankle_monitor",
           () -> new AnkleMonitorItem(ModArmorMaterials.ANKLE_MONITOR, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ANKLE_MONITOR_TRACKER = ITEMS.register("ankle_monitor_tracker",
            () -> new TrackerItem(new Item.Properties().stacksTo(1)));


    /* KEYS */

    public static final RegistryObject<Item> ANKLE_MONITOR_KEY = ITEMS.register("ankle_monitor_key",
            () -> new AnkleMonitorKey(new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> JUMPSUIT_KEY = ITEMS.register("jumpsuit_key",
            () -> new JumpsuitKey(new Item.Properties().stacksTo(1)));







    public static void register(IEventBus bus) {
                ITEMS.register(bus);
        }
}
