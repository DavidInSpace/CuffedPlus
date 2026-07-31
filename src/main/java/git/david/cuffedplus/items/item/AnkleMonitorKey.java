package git.david.cuffedplus.items.item;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class AnkleMonitorKey extends Item {

    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    public AnkleMonitorKey(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);
        assert Minecraft.getInstance().player != null;

        if (player.isCrouching()) return InteractionResultHolder.fail(itemInHand);
        if (level.isClientSide) return InteractionResultHolder.fail(itemInHand);

        if (currentChest.getItem() instanceof AnkleMonitorItem && currentChest.getOrCreateTag().getBoolean("CanBeLocked") && currentChest.getOrCreateTag().getBoolean("Locked")) {
            // UNLOCKING
            if (config.getPlayersOwnAnkleMonitorLockBehavior().equals("onlyLock".toLowerCase()) || config.getPlayersOwnAnkleMonitorLockBehavior().equals("none")) {
                player.playSound(SoundEvents.IRON_DOOR_CLOSE, 1, (float) Math.random() * 1.5F);
                player.displayClientMessage(Component.literal("× You can not unlock your ankle monitor ×").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }
            currentChest.getOrCreateTag().putBoolean("Locked", false);
        } else if (currentChest.getItem() instanceof AnkleMonitorItem && currentChest.getOrCreateTag().getBoolean("CanBeLocked") && !currentChest.getOrCreateTag().getBoolean("Locked")) {
            // LOCKING
            if (config.getPlayersOwnAnkleMonitorLockBehavior().equals("onlyUnlock".toLowerCase()) || config.getPlayersOwnAnkleMonitorLockBehavior().equals("none")) {
                player.displayClientMessage(Component.literal("× You can not lock your ankle monitor ×").withStyle(ChatFormatting.RED), true);
                return InteractionResultHolder.fail(itemInHand);
            }
            currentChest.getOrCreateTag().putBoolean("Locked", true);
        }

        return InteractionResultHolder.success(itemInHand);
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (!user.isCrouching()) return InteractionResult.FAIL;
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;

        ItemStack targetChest = target.getItemBySlot(EquipmentSlot.CHEST);

        if (targetChest.getItem() instanceof AnkleMonitorItem && targetChest.getOrCreateTag().getBoolean("CanBeLocked") && targetChest.getOrCreateTag().getBoolean("Locked")) {
            if (target.getTags().contains("prisoner") && (config.getOtherPrisonersAnkleMonitorLockBehavior().equals("onlyLock") || config.getOtherPrisonersAnkleMonitorLockBehavior().equals("none"))) return InteractionResult.FAIL;
            if (config.getOtherPlayersAnkleMonitorLockBehavior().equals("onlyLock") || config.getOtherPlayersAnkleMonitorLockBehavior().equals("none")) return InteractionResult.FAIL;
            targetChest.getOrCreateTag().putBoolean("Locked", false);
        } else if (targetChest.getItem() instanceof AnkleMonitorItem && targetChest.getOrCreateTag().getBoolean("CanBeLocked") && !targetChest.getOrCreateTag().getBoolean("Locked")) {
            if (target.getTags().contains("prisoner") && (config.getOtherPrisonersAnkleMonitorLockBehavior().equals("onlyUnLock") || config.getOtherPrisonersAnkleMonitorLockBehavior().equals("none"))) return InteractionResult.FAIL;
            if (config.getOtherPlayersAnkleMonitorLockBehavior().equals("onlyUnlock".toLowerCase()) || config.getOtherPlayersAnkleMonitorLockBehavior().equals("none")) return InteractionResult.FAIL;
            targetChest.getOrCreateTag().putBoolean("Locked", true);
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.literal("Used to lock and unlock ankle monitors with the lock modifier").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
