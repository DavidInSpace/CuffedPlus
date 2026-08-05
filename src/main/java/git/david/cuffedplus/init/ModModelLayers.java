package git.david.cuffedplus.init;




/*

    This class is used to store and register the model layers used by your restraints, this can be done in the model classes but I find it easier to keep everything 
    in one place and to register them all at once.

    This is exactly the same as registering ModelLayerLocations for entities.

 */

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.items.restraints.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ModModelLayers {

    // WOOD
    public static final ModelLayerLocation WOOD_CUFFS_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "wood_cuffs_arms_layer"), "main");
    public static final ModelLayerLocation WOOD_CUFFS_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "wood_cuffs_legs_layer"), "main");

    // GOLD
    public static final ModelLayerLocation GOLD_CUFFS_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "gold_cuffs_arms_layer"), "main");
    public static final ModelLayerLocation GOLD_CUFFS_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "gold_cuffs_legs_layer"), "main");

    // EMERALD
    public static final ModelLayerLocation EMERALD_CUFFS_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "emerald_cuffs_arms_layer"), "main");
    public static final ModelLayerLocation EMERALD_CUFFS_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "emerald_cuffs_legs_layer"), "main");

    // DIAMOND
    public static final ModelLayerLocation DIAMOND_CUFFS_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "diamond_cuffs_arms_layer"), "main");
    public static final ModelLayerLocation DIAMOND_CUFFS_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "diamond_cuffs_legs_layer"), "main");

    // NETHERITE
    public static final ModelLayerLocation NETHERITE_CUFFS_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "netherite_cuffs_arms_layer"), "main");
    public static final ModelLayerLocation NETHERITE_CUFFS_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "netherite_cuffs_legs_layer"), "main");

    // BEDROCK
    public static final ModelLayerLocation BEDROCK_CUFFS_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "bedrock_cuffs_arms_layer"), "main");
    public static final ModelLayerLocation BEDROCK_CUFFS_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "bedrock_cuffs_legs_layer"), "main");

    public static final ModelLayerLocation HAZARD_TAPE_HEAD_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "hazard_tape_head_layer"), "main");
    public static final ModelLayerLocation HAZARD_TAPE_ARMS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "hazard_tape_arms_layer"), "main");
    public static final ModelLayerLocation HAZARD_TAPE_LEGS_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "hazard_tape_legs_layer"), "main");

    public static final ModelLayerLocation ANKLE_MONITOR_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "ankle_monitor_layer"), "main");
    // public static final ModelLayerLocation ANKLE_MONITOR_CLEAN_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "ankle_monitor_clean_layer"), "main");

    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // WOOD

        event.registerLayerDefinition(WOOD_CUFFS_ARMS_LAYER, WoodCuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(WOOD_CUFFS_LEGS_LAYER, WoodCuffsLegsModel::createArmorLayer);

        // GOLD
        event.registerLayerDefinition(GOLD_CUFFS_ARMS_LAYER, GoldCuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(GOLD_CUFFS_LEGS_LAYER, GoldCuffsLegsModel::createArmorLayer);

        // EMERALD
        event.registerLayerDefinition(EMERALD_CUFFS_ARMS_LAYER, EmeraldCuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(EMERALD_CUFFS_LEGS_LAYER, EmeraldCuffsLegsModel::createArmorLayer);

        // DIAMOND
        event.registerLayerDefinition(DIAMOND_CUFFS_ARMS_LAYER, DiamondCuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(DIAMOND_CUFFS_LEGS_LAYER, DiamondCuffsLegsModel::createArmorLayer);

        // NETHERITE
        event.registerLayerDefinition(NETHERITE_CUFFS_ARMS_LAYER, NetheriteCuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(NETHERITE_CUFFS_LEGS_LAYER, NetheriteCuffsLegsModel::createArmorLayer);

        // BEDROCK
        event.registerLayerDefinition(BEDROCK_CUFFS_ARMS_LAYER, BedrockCuffsArmsModel::createArmorLayer);
        event.registerLayerDefinition(BEDROCK_CUFFS_LEGS_LAYER, BedrockCuffsLegsModel::createArmorLayer);

        event.registerLayerDefinition(HAZARD_TAPE_HEAD_LAYER, HazardTapeHeadModel::createArmorLayer);
        event.registerLayerDefinition(HAZARD_TAPE_ARMS_LAYER, HazardTapeArmsModel::createArmorLayer);
        event.registerLayerDefinition(HAZARD_TAPE_LEGS_LAYER, HazardTapeLegsModel::createArmorLayer);

        event.registerLayerDefinition(ANKLE_MONITOR_LAYER, AnkleMonitorModel::createArmorLayer);

        // event.registerLayerDefinition(ANKLE_MONITOR_LAYER, AnkleMonitorModel::createArmorLayer);
        //  event.registerLayerDefinition(ANKLE_MONITOR_CLEAN_LAYER, AnkleMonitorModel::createArmorLayer);


    }

}
