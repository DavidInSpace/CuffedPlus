package git.david.cuffedplus.init;

import com.lazrproductions.cuffed.CuffedMod;
import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;
import com.lazrproductions.cuffed.restraints.custom.*;
import git.david.cuffedplus.items.item.base.AnkleMonitorItem;
import git.david.cuffedplus.items.item.base.JumpsuitItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ModStatistics {

 /*   private static final DeferredRegister<ResourceLocation> REGISTER = DeferredRegister.create(
            Registries.CUSTOM_STAT, CuffedMod.MODID
    );
    private static final List<Runnable> RUN_IN_SETUP = new ArrayList<>();

    public static final RegistryObject<ResourceLocation> PRISON_JUMPSUITS_LOCKED = registerCustomStat("prison_jumpsuits_locked", StatFormatter.DEFAULT);
    public static final RegistryObject<ResourceLocation> PRISON_JUMPSUITS_UNLOCKED = registerCustomStat("prison_jumpsuits_unlocked", StatFormatter.DEFAULT);
    public static final RegistryObject<ResourceLocation> PRISON_JUMPSUITS_PUT_ON_OTHERS = registerCustomStat("prison_jumpsuits_put_on_others", StatFormatter.DEFAULT);
    public static final RegistryObject<ResourceLocation> PRISON_JUMPSUITS_TIME_WORN = registerCustomStat("ankle_monitors_put_on_others", StatFormatter.DEFAULT);

    public static final RegistryObject<ResourceLocation> ANKLE_MONITORS_LOCKED = registerCustomStat("ankle_monitors_locked", StatFormatter.DEFAULT);
    public static final RegistryObject<ResourceLocation> ANKLE_MONITORS_UNLOCKED = registerCustomStat("ankle_monitors_unlocked", StatFormatter.DEFAULT);
    public static final RegistryObject<ResourceLocation> ANKLE_MONITORS_PUT_ON_OTHERS = registerCustomStat("ankle_monitors_put_on_others", StatFormatter.DEFAULT);
    public static final RegistryObject<ResourceLocation> ANKLE_MONITORS_TIME_WORN = registerCustomStat("ankle_monitors_put_on_others", StatFormatter.DEFAULT);




    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }

    public static void setup()
    {
        RUN_IN_SETUP.forEach(Runnable::run);
    }

    private static RegistryObject<ResourceLocation> registerCustomStat(String name, StatFormatter formatter)
    {
        return REGISTER.register(name, () -> {
            ResourceLocation regName = ResourceLocation.fromNamespaceAndPath(CuffedMod.MODID, name);
            RUN_IN_SETUP.add(() -> Stats.CUSTOM.get(regName, formatter));
            return regName;
        });
    }

    public static void awardGearLocked(@Nonnull ServerPlayer player, @Nonnull Item restraint) {
        if(restraint instanceof JumpsuitItem)
            player.awardStat(PRISON_JUMPSUITS_LOCKED.get(), 1);
        else if(restraint instanceof AnkleMonitorItem)
            player.awardStat(ANKLE_MONITORS_LOCKED.get(), 1);
    }

    public static void awardGearUnlocked(@Nonnull ServerPlayer player, @Nonnull Item restraint) {
        if(restraint instanceof JumpsuitItem)
            player.awardStat(PRISON_JUMPSUITS_UNLOCKED.get(), 1);
        else if(restraint instanceof AnkleMonitorItem)
            player.awardStat(ANKLE_MONITORS_UNLOCKED.get(), 1);
    }

    public static void awardGearPutOnOthers(@Nonnull ServerPlayer player, @Nonnull Item restraint) {
        if(restraint instanceof JumpsuitItem)
            player.awardStat(PRISON_JUMPSUITS_PUT_ON_OTHERS.get(), 1);
        else if(restraint instanceof AnkleMonitorItem)
            player.awardStat(ANKLE_MONITORS_PUT_ON_OTHERS.get(), 1);
    }

    public static void awardTimeGearWorn(@Nonnull ServerPlayer player, @Nonnull Item restraint) {
        if(restraint instanceof JumpsuitItem)
            player.awardStat(PRISON_JUMPSUITS_TIME_WORN.get(), 1);
        else if(restraint instanceof AnkleMonitorItem)
            player.awardStat(ANKLE_MONITORS_TIME_WORN.get(), 1);
    } */
}
