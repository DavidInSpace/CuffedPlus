package git.david.cuffedplus.items.item.base;

import com.lazrproductions.cuffed.CuffedMod;
import git.david.cuffedplus.config.ICuffedPlusServerConfigMixin;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
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
import java.util.UUID;

import static git.david.cuffedplus.utils.GeneralUtils.displayClientMessage;
import static git.david.cuffedplus.utils.GeneralUtils.extractPlayerName;

public class TrackerItem extends Item {
    ICuffedPlusServerConfigMixin config = (ICuffedPlusServerConfigMixin) CuffedMod.SERVER_CONFIG;

    public TrackerItem(Properties p) {
        super(p);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack ankleMonitor = player.getItemBySlot(EquipmentSlot.FEET);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(stack);
        if (level.isClientSide) return InteractionResultHolder.fail(stack);

        MinecraftServer server = player.getServer();
        assert server != null;


        ItemStack handItem = player.getItemInHand(hand);

        if (ankleMonitor.getItem() instanceof AnkleMonitorItem) {

            assert handItem.getTag() != null;
            assert ankleMonitor.getTag() != null;
            if (!handItem.getOrCreateTag().hasUUID("targetUUID") && !ankleMonitor.getOrCreateTag().hasUUID("ownerUUID")) {

                if (config.getPlayersOwnTrackerBindingBehavior().equals("onlyUnbind".toLowerCase()) || (config.getPlayersOwnTrackerBindingBehavior().equals("none"))) {
                    player.displayClientMessage(Component.literal("× You can't bind the tracker to yourself ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResultHolder.fail(stack);
                }

                if (player.getTags().contains("prisoner") && config.getPrisonersOwnTrackerBindingBehavior().equals("onlyUnbind".toLowerCase()) || config.getPrisonersOwnTrackerBindingBehavior().equals("none")) {
                    player.displayClientMessage(Component.literal("× You are a prisoner!  Prisoners can't bind the tracker to themselves ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResultHolder.fail(stack);
                }


                Minecraft.getInstance().player.displayClientMessage(Component.literal("Ankle monitor bound to " + extractPlayerName(String.valueOf(player.getName()))).withStyle(ChatFormatting.GREEN), false);

                UUID playerUUID = player.getUUID();
                String playerName = GeneralUtils.extractPlayerName(player.getName().toString());
                ankleMonitor.getOrCreateTag().putUUID("ownerUUID", playerUUID);
                ankleMonitor.getOrCreateTag().putString("ownerName", playerName);


                handItem.getOrCreateTag().putUUID("targetUUID", playerUUID);
                handItem.getOrCreateTag().putString("targetName", playerName);

            } else if (handItem.getOrCreateTag().getUUID("targetUUID") == ankleMonitor.getOrCreateTag().getUUID("ownerUUID")) {

                if (config.getPlayersOwnTrackerBindingBehavior().equals("onlyBind".toLowerCase()) || (config.getPlayersOwnTrackerBindingBehavior().equals("none"))) {
                    player.displayClientMessage(Component.literal("× You can't unbind your own tracker ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResultHolder.fail(stack);
                }

                if (player.getTags().contains("prisoner") && config.getPrisonersOwnTrackerBindingBehavior().equals("onlyBind".toLowerCase()) || config.getPrisonersOwnTrackerBindingBehavior().equals("none")) {
                    player.displayClientMessage(Component.literal("× You are a prisoner!  Prisoners can't unbind their own tracker ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResultHolder.fail(stack);
                }


                Minecraft.getInstance().player.displayClientMessage(Component.literal("Ankle monitor unbound").withStyle(ChatFormatting.RED), false);

                ankleMonitor.getOrCreateTag().remove("ownerUUID");
                ankleMonitor.getOrCreateTag().remove("ownerName");

                ankleMonitor.getOrCreateTag().remove("targetUUID");
                ankleMonitor.getOrCreateTag().remove("targetName");
            }
        }
        System.out.println(handItem.getOrCreateTag().hasUUID("targetUUID"));
        if (!handItem.getOrCreateTag().hasUUID("targetUUID")) return InteractionResultHolder.fail(stack);

        Player targetPlayer = server.getPlayerList().getPlayer(handItem.getOrCreateTag().getUUID("targetUUID"));
        assert targetPlayer != null;
        displayClientMessage(player, "Name: " + extractPlayerName(handItem.getOrCreateTag().getString("targetName")), ChatFormatting.AQUA);

        if (targetPlayer.getTags().contains("prisoner")) {
            displayClientMessage(player, "Role: [INMATE]", ChatFormatting.GOLD);
        } else if (targetPlayer.getTags().contains("officer")) {
            displayClientMessage(player, "Role: [OFFICER]", ChatFormatting.BLUE);
        } else {
            displayClientMessage(player, "Role: None", ChatFormatting.WHITE);
        }

        displayClientMessage(player, "Position: " + Math.round(targetPlayer.getPosition(1).x) + " " + Math.round(targetPlayer.getPosition(1).y) + " " + Math.round(targetPlayer.getPosition(1).z), ChatFormatting.YELLOW);
        displayClientMessage(player, "Health: " + (int) targetPlayer.getHealth() + " ❤", ChatFormatting.RED);


        return InteractionResultHolder.success(stack);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack ankleMonitor = target.getItemBySlot(EquipmentSlot.FEET);

        if (user.isCrouching()) return InteractionResult.FAIL;
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;

        if (ankleMonitor.getItem() instanceof AnkleMonitorItem) {
            if (!stack.getOrCreateTag().hasUUID("targetUUID") && !ankleMonitor.getOrCreateTag().hasUUID("ownerUUID")) {
                if (config.getOtherPlayersTrackerBindingBehavior().equals("onlyUnbind".toLowerCase()) || (config.getOtherPlayersTrackerBindingBehavior().equals("none"))) {
                    user.displayClientMessage(Component.literal("× You can't put bind trackers to others ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResult.FAIL;
                }

                if (user.getTags().contains("prisoner") && config.getOtherPrisonersTrackerBindingBehavior().equals("onlyUnbind".toLowerCase()) || config.getOtherPrisonersTrackerBindingBehavior().equals("none")) {
                    user.displayClientMessage(Component.literal("× You are a prisoner!  Prisoners can't bind trackers to others ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResult.FAIL;
                }

                assert Minecraft.getInstance().player != null;
                Minecraft.getInstance().player.displayClientMessage(Component.literal("Ankle monitor bound to " + extractPlayerName(String.valueOf(target.getName()))).withStyle(ChatFormatting.GREEN), false);
                ItemStack handItem = user.getItemInHand(hand);

                String targetName = target.getName().toString();

                UUID targetUUID = target.getUUID();
                String userName = target.getName().toString();
                UUID userUUID = target.getUUID();

                ankleMonitor.getOrCreateTag().putUUID("ownerUUID", userUUID);
                ankleMonitor.getOrCreateTag().putString("ownerName", userName);

                handItem.getOrCreateTag().putUUID("targetUUID", targetUUID);
                handItem.getOrCreateTag().putString("targetName", targetName);
            } else if (stack.getOrCreateTag().getUUID("targetUUID") == ankleMonitor.getOrCreateTag().getUUID("ownerUUID")) {
                if (config.getOtherPlayersTrackerBindingBehavior().equals("onlyBind".toLowerCase()) || (config.getOtherPlayersTrackerBindingBehavior().equals("none"))) {
                    user.displayClientMessage(Component.literal("× You can't put unbind others trackers ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResult.FAIL;
                }

                if (user.getTags().contains("prisoner") && config.getOtherPrisonersTrackerBindingBehavior().equals("onlyBind".toLowerCase()) || config.getOtherPrisonersTrackerBindingBehavior().equals("none")) {
                    user.displayClientMessage(Component.literal("× You are a prisoner!  Prisoners can't unbind others trackers ×").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), true);
                    return InteractionResult.FAIL;
                }
            }
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (stack.getItem() instanceof TrackerItem) {
            String targetPlayerName = stack.getOrCreateTag().getString("targetName");
            tooltip.add(Component.literal("Bound to: ").withStyle(ChatFormatting.GRAY).append(Component.literal(extractPlayerName(targetPlayerName)).withStyle(ChatFormatting.YELLOW)));

            super.appendHoverText(stack, level, tooltip, flag);
        }
    }

}
