package git.david.cuffedplus.command;

import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.RestrainableCapability;
import com.lazrproductions.cuffed.restraints.base.RestraintType;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import git.david.cuffedplus.items.item.base.TimeLockItem;
import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.server.command.EnumArgument;

import java.util.Objects;

public class CuffedPlusCommand {
    public CuffedPlusCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ctx) {
        dispatcher.register(
                Commands.literal("cuffed").requires((source) -> {
                            return source.hasPermission(2) || !source.isPlayer();
                        }).then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.literal("time_lock")
                                        .then(Commands.argument("type", EnumArgument.enumArgument(RestraintType.class))
                                                .then(Commands.argument("seconds", IntegerArgumentType.integer(0, 60))
                                                        .executes(this::TimeLockRestraint)
                                                        .then(Commands.argument("minutes", IntegerArgumentType.integer(0, 60))
                                                                .executes(this::TimeLockRestraint)
                                                                .then(Commands.argument("hours", IntegerArgumentType.integer(0))
                                                                        .executes(this::TimeLockRestraint)))))))
                        .then(Commands.literal("plus")
                                .then(Commands.literal("time_lock")
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("seconds", IntegerArgumentType.integer(0, 60))
                                                        .then(Commands.argument("minutes", IntegerArgumentType.integer(0, 60))
                                                                .then(Commands.argument("hours", IntegerArgumentType.integer(0))
                                                                        .executes(this::setTimeModifierTime))))))
                                .then(Commands.literal("roles")
                                        .then(Commands.literal("get")
                                                .then(Commands.argument("player", EntityArgument.player())
                                                        .executes(this::executeGetRole)))
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("player", EntityArgument.player())
                                                        .then(Commands.literal("prisoner")
                                                                .executes(this::executeApplyPrisonerRole))
                                                        .then(Commands.literal("officer")
                                                                .executes(this::executeApplyOfficerRole))
                                                        .then(Commands.literal("none")
                                                                .executes(this::executeApplyNoneRole)))))));
    }


    private int TimeLockRestraint(CommandContext<CommandSourceStack> ctx) {
        try {
            int seconds = IntegerArgumentType.getInteger(ctx, "seconds");
            int minutes = IntegerArgumentType.getInteger(ctx, "minutes");
            int hours = IntegerArgumentType.getInteger(ctx, "hours");
            ServerPlayer sender = ctx.getSource().getPlayer();
            if (seconds == 0 && minutes == 0 && hours == 0) {
                assert sender != null;
                sender.displayClientMessage(Component.translatable("command.cuffedplus.time.too_little"), true);
                return 1;
            }
            ServerPlayer player = EntityArgument.getPlayer(ctx, "player");

            RestraintType type = ctx.getArgument("type", RestraintType.class);
            RestrainableCapability playerCap = (RestrainableCapability) CuffedAPI.Capabilities.getRestrainableCapability(player);
            String pKey = type == RestraintType.Arm ? "info.cuffed.arms" : type == RestraintType.Head ? "info.cuffed.head" : "info.cuffed.legs";
            if (!playerCap.isRestrained(type)) {
                assert sender != null;
                if (sender.getUUID() != player.getUUID()) {
                    sender.displayClientMessage(Component.translatable("command.cuffedplus.restraint.missing", player.getName(), Component.translatable(pKey)).withStyle(ChatFormatting.RED), false);
                } else {
                    sender.displayClientMessage(Component.translatable("command.cuffedplus.restraint.own_missing", Component.translatable(pKey)).withStyle(ChatFormatting.RED), false);
                }
                return 1;
            }

            ItemStack restraintStack = Objects.requireNonNull(playerCap.getRestraint(type)).saveToItemStack();
            long ticks_time = (seconds * 20L) + (minutes * 20L * 60L) + (hours * 20L * 60L * 60L);
            restraintStack.getOrCreateTag().putBoolean("DropTimeLock", false);
            restraintStack.getOrCreateTag().putLong("Time", ticks_time);
            restraintStack.getOrCreateTag().putBoolean("Timer", true);
            assert sender != null;
            if (sender.getUUID() != player.getUUID()) {
                sender.displayClientMessage(Component.literal("Time lock applied to " + player.getName() + "'s ").append(Component.translatable(pKey)).append(Component.literal(" lasting " + seconds + "s : " + minutes + "m : " + hours + "h").withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GREEN), false);
                player.displayClientMessage(Component.literal("A time lock was applied to your ").append(Component.translatable(pKey)).append(Component.literal(" lasting " + seconds + "s : " + minutes + "m : " + hours + "h").withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GREEN), false);
            } else {
                sender.displayClientMessage(Component.literal("You applied a time lock on to your ").append(Component.translatable(pKey)).append(Component.literal(" lasting " + seconds + "s : " + minutes + "m : " + hours + "h").withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        } catch (CommandSyntaxException exception) {
            return 1;
        }


    }


    private int setTimeModifierTime(CommandContext<CommandSourceStack> ctx) {
        if (!ctx.getSource().isPlayer()) return 1;
        Player player = ctx.getSource().getPlayer();
        assert player != null;
        player.displayClientMessage(Component.literal("setting time"), false);
        ItemStack itemInMainHand = player.getMainHandItem();
        player.displayClientMessage(Component.literal(itemInMainHand.getItem() + " : " + itemInMainHand.getItem().getDefaultInstance() + " : " + itemInMainHand.getOrCreateTag().getBoolean("Timer")), false);
        // Check whether the item in hand is a Restraint Item with a Timer Modifier Applied to it
        if (!(itemInMainHand.getItem() instanceof TimeLockItem timeLock)) {
            player.displayClientMessage(Component.literal("ERROR: You must hold a time lock to set the time.").withStyle(ChatFormatting.RED), false);
            return 1;
        }

        int seconds = IntegerArgumentType.getInteger(ctx, "seconds");
        int minutes = IntegerArgumentType.getInteger(ctx, "minutes");
        int hours = IntegerArgumentType.getInteger(ctx, "hours");

        long ticks_time = (seconds * 20L) + (minutes * 20L * 60L) + (hours * 20L * 60L * 60L);

        // set the time
        timeLock.seconds = seconds;
        timeLock.minutes = minutes;
        timeLock.hours = hours;
        timeLock.ticks_time = ticks_time;
        itemInMainHand.getOrCreateTag().putLong("Time", timeLock.ticks_time);
        player.displayClientMessage(Component.literal("Time of " + seconds + "s : " + minutes + "m : " + hours + "h : (" + ticks_time + " ticks) applied").withStyle(ChatFormatting.GREEN), false);
        return 0;
    }


    private int executeGetRole(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
            ServerPlayer sender = ctx.getSource().getPlayer();
            assert sender != null;

            String[] tags = player.getTags().toArray(new String[0]);

            if (player.getTags().contains("prisoner") || player.getTags().contains("officer")) {
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " has following roles:").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD), false);
                for (String tag : tags) {
                    if (tag.equals("prisoner") || tag.equals("officer")) {
                        sender.displayClientMessage(Component.literal(tag).withStyle(ChatFormatting.YELLOW), false);
                    }
                }
            } else {
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " doesn't have any roles").withStyle(ChatFormatting.YELLOW), false);
            }

            player.refreshDisplayName();
            player.refreshTabListName();

            return 1;
        } catch (CommandSyntaxException e) {
            return 0;
        }
    }


    private int executeApplyPrisonerRole(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
            ServerPlayer sender = ctx.getSource().getPlayer();
            assert sender != null;

            if (player.getTags().contains("prisoner")) {
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " is already a prisoner").withStyle(ChatFormatting.RED), false);
            } else {
                player.removeTag("officer");
                player.addTag("prisoner");
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " is now a prisoner").withStyle(ChatFormatting.GREEN), false);
                player.displayClientMessage(Component.literal("You are now a prisoner").withStyle(ChatFormatting.GOLD), false);
            }

            player.refreshDisplayName();
            player.refreshTabListName();

            return 1;
        } catch (CommandSyntaxException e) {
            return 0;
        }
    }


    private int executeApplyOfficerRole(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
            ServerPlayer sender = ctx.getSource().getPlayer();
            assert sender != null;

            if (player.getTags().contains("officer")) {
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " is already an officer").withStyle(ChatFormatting.RED), false);
            } else {
                player.removeTag("prisoner");
                player.addTag("officer");
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " is now an officer").withStyle(ChatFormatting.GREEN), false);
                player.displayClientMessage(Component.literal("You are now an officer").withStyle(ChatFormatting.BLUE), false);
            }

            player.refreshDisplayName();
            player.refreshTabListName();

            return 1;
        } catch (CommandSyntaxException e) {
            return 0;
        }
    }


    private int executeApplyNoneRole(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
            ServerPlayer sender = ctx.getSource().getPlayer();
            assert sender != null;

            if (!player.getTags().contains("prisoner") && !player.getTags().contains("officer")) {
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " already doesn't have any role").withStyle(ChatFormatting.RED), false);
            } else {
                player.removeTag("prisoner");
                player.removeTag("officer");
                sender.displayClientMessage(Component.literal(GeneralUtils.extractPlayerName(String.valueOf(player.getName())) + " doesn't have any role now").withStyle(ChatFormatting.GREEN), false);
                player.displayClientMessage(Component.literal("you don't have any role now").withStyle(ChatFormatting.GOLD), false);
            }

            player.refreshDisplayName();
            player.refreshTabListName();

            return 1;
        } catch (CommandSyntaxException e) {
            return 0;
        }
    }

}

