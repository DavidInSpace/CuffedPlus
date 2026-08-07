package git.david.cuffedplus.logic;

import git.david.cuffedplus.items.item.base.AnkleMonitorItem;
import git.david.cuffedplus.items.item.base.JumpsuitItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GearModifiersLogic {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        ItemStack itemInChest = event.player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack itemInFeet = event.player.getItemBySlot(EquipmentSlot.FEET);
        if ((itemInChest.getItem() instanceof JumpsuitItem || itemInFeet.getItem() instanceof AnkleMonitorItem) && itemInChest.getOrCreateTag().getBoolean("HighVisibility")) {
            event.player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 8, 0, false, false));
        }
    }

}
