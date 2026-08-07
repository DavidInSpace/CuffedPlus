package git.david.cuffedplus.logic;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.items.item.base.AnkleMonitorItem;
import git.david.cuffedplus.items.item.base.JumpsuitItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class KeepLockedGearOnDeathLogic {
    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    private ItemStack keepItem;

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().getServer() == null) return;
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        ItemStack playerChest = player.getItemBySlot(EquipmentSlot.CHEST);

        keepItem = playerChest;
        player.setItemSlot(EquipmentSlot.CHEST, playerChest);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity().getServer() == null) return;
        if (keepItem == null) return;
        if (!(keepItem.getItem() instanceof JumpsuitItem) && !(keepItem.getItem() instanceof AnkleMonitorItem)) return;
        if (!(keepItem.getOrCreateTag().getBoolean("CanBeLocked")) || !(keepItem.getOrCreateTag().getBoolean("Locked"))) return;
        if (!config.keepLockedGearOnDeath()) return;
        GameRules.Value<GameRules.BooleanValue> isKeepInventoryOn = Objects.requireNonNull(event.getEntity().getServer()).getGameRules().getRule(GameRules.RULE_KEEPINVENTORY);
        if (Boolean.parseBoolean(String.valueOf(String.valueOf(isKeepInventoryOn).equals("true")))) return; // if keep inventory is on then return since it already does the job

        event.getEntity().setItemSlot(EquipmentSlot.CHEST, keepItem);
    }


    @SubscribeEvent
    public void onPlayerDropItem(LivingDropsEvent event) {
        if (event.getEntity().getServer() == null) return;
        if (keepItem == null) return;
        if (!(event.getEntity() instanceof Player)) return;
        if (!(keepItem.getItem() instanceof JumpsuitItem) && !(keepItem.getItem() instanceof AnkleMonitorItem)) return;
        if (!(keepItem.getOrCreateTag().getBoolean("CanBeLocked")) || !(keepItem.getOrCreateTag().getBoolean("Locked"))) return; // If the jumpsuit isnt locked return
        if (!config.keepLockedGearOnDeath()) return;
        GameRules.Value<GameRules.BooleanValue> isKeepInventoryOn = Objects.requireNonNull(event.getEntity().getServer()).getGameRules().getRule(GameRules.RULE_KEEPINVENTORY);
        if (Boolean.parseBoolean(String.valueOf(String.valueOf(isKeepInventoryOn).equals("true")))) return; // if keep inventory is on then return since it already does the job

        for (ItemEntity drop : event.getDrops()) {
            if (drop.getItem().getItem() instanceof JumpsuitItem) {
                drop.remove(Entity.RemovalReason.DISCARDED);
                break;
            }
        }
    }

}
