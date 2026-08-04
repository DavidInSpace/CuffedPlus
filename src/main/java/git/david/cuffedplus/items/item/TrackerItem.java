package git.david.cuffedplus.items.item;

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

    /* THIS IS THE ITEM THAT WILL CONNECT TO AN ANKLE MONITOR TO CONTROL IT*/

    public TrackerItem(Properties p) {
        super(p);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack ankleMonitor = player.getItemBySlot(EquipmentSlot.FEET);
        assert Minecraft.getInstance().player != null;

        if (!player.isCrouching()) return InteractionResultHolder.fail(stack);
        if (level.isClientSide) InteractionResultHolder.fail(stack);

        MinecraftServer server = player.getServer();
        assert server != null;


        ItemStack handItem = player.getItemInHand(hand);

        if (!(ankleMonitor.getItem() instanceof AnkleMonitorItem)) InteractionResultHolder.fail(stack);

        assert handItem.getTag() != null;
        assert ankleMonitor.getTag() != null;
        if (!handItem.getTag().hasUUID("targetUUID") && !ankleMonitor.getTag().hasUUID("ownerUUID")) {
            Minecraft.getInstance().player.displayClientMessage(Component.literal("Ankle monitor bound to " + extractPlayerName(String.valueOf(player.getName()))).withStyle(ChatFormatting.GREEN), false);

            UUID playerUUID = player.getUUID();
            String playerName = GeneralUtils.extractPlayerName(player.getName().toString());

            ankleMonitor.getOrCreateTag().putUUID("ownerUUID", playerUUID);
            ankleMonitor.getOrCreateTag().putString("ownerName", playerName);


            handItem.getOrCreateTag().putUUID("targetUUID", playerUUID);
            handItem.getOrCreateTag().putString("targetName", playerName);

        }


        Player targetPlayer = server.getPlayerList().getPlayer(handItem.getOrCreateTag().getUUID("targetUUID"));
        assert targetPlayer != null;
        displayClientMessage(targetPlayer, "Name: " + extractPlayerName(String.valueOf(targetPlayer.getName())), ChatFormatting.AQUA);

        if (targetPlayer.getTags().contains("prisoner")) {
            displayClientMessage(targetPlayer, "Role: [INMATE]", ChatFormatting.GOLD);
        } else if (targetPlayer.getTags().contains("officer")) {
            displayClientMessage(targetPlayer, "Role: [OFFICER]", ChatFormatting.BLUE);
        } else {
            displayClientMessage(targetPlayer, "Role: None", ChatFormatting.WHITE);
        }

        displayClientMessage(targetPlayer, "Position: " + Math.round(targetPlayer.getPosition(1).x) + " " + Math.round(targetPlayer.getPosition(1).y) + " " + Math.round(targetPlayer.getPosition(1).z), ChatFormatting.BLUE);
        displayClientMessage(targetPlayer, "Health: " + targetPlayer.getHealth() + " ❤", ChatFormatting.RED);


        return InteractionResultHolder.success(stack);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        ItemStack ankleMonitor = target.getItemBySlot(EquipmentSlot.FEET);

        if (user.isCrouching()) return InteractionResult.FAIL;
        if (user.level().isClientSide && !(target instanceof Player)) return InteractionResult.FAIL;

        if (ankleMonitor.getItem() instanceof AnkleMonitorItem) {
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

        } else {
            // do something
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (stack.getItem() instanceof TrackerItem) {
            String targetPlayerName = stack.getOrCreateTag().getString("targetName");
            tooltip.add(Component.literal("Bound to: ").withStyle(ChatFormatting.GRAY).append(Component.literal(targetPlayerName).withStyle(ChatFormatting.YELLOW)));

            super.appendHoverText(stack, level, tooltip, flag);
        }
    }

}
