package git.david.cuffedplus.init;

import git.david.cuffedplus.CuffedPlusMain;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegisterEvent;

/*
    Very basic sound event registry, nothing special to note here :)
 */

public class ModSounds {

    public static final SoundEvent WOOD_CUFFS_EQUIP = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_wood_cuffs"));
    public static final SoundEvent GOLD_CUFFS_EQUIP = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_gold_cuffs"));
    public static final SoundEvent DIAMOND_CUFFS_EQUIP = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_diamond_cuffs"));
    public static final SoundEvent NETHERITE_CUFFS_EQUIP = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_netherite_cuffs"));



    public static void register(RegisterEvent event) {
        event.register(Keys.SOUND_EVENTS, x -> {
            x.register(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_wood_cuffs"), WOOD_CUFFS_EQUIP);
            x.register(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_gold_cuffs"), GOLD_CUFFS_EQUIP);
            x.register(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_diamond_cuffs"), DIAMOND_CUFFS_EQUIP);
            x.register(ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "restraint.apply_netherite_cuffs"), NETHERITE_CUFFS_EQUIP);
        });
    }
}

