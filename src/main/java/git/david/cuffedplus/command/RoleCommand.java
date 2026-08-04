package git.david.cuffedplus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class RoleCommand {
    public RoleCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ctx) {
        dispatcher.register(
                Commands.literal("cuffed").requires((source) -> {
                            return source.hasPermission(2) || !source.isPlayer();
                        }).then(Commands.literal("plus")
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


    private int executeGetRole(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
            ServerPlayer sender = ctx.getSource().getPlayer();
            assert sender != null;

            String[] tags = player.getTags().toArray(new String[0]);


            if (player.getTags().contains("prisoner") || player.getTags().contains("officer")) {
                sender.displayClientMessage(Component.literal(String.valueOf(player.getDisplayName())).withStyle(ChatFormatting.YELLOW), false);
                for (String tag : tags) {
                    sender.displayClientMessage(Component.literal(tag).withStyle(ChatFormatting.YELLOW), false);
                }
            } else {
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " doesn't have any roles").withStyle(ChatFormatting.YELLOW), false);
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
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " is already a prisoner").withStyle(ChatFormatting.RED), false);
            } else {
                player.removeTag("officer");
                player.addTag("prisoner");
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " is now a prisoner").withStyle(ChatFormatting.GREEN), false);
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
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " is already an officer").withStyle(ChatFormatting.RED), false);
            } else {
                player.removeTag("prisoner");
                player.addTag("officer");
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " is now an officer").withStyle(ChatFormatting.GREEN), false);
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
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " already doesn't have any role").withStyle(ChatFormatting.RED), false);
            } else {
                player.removeTag("prisoner");
                player.removeTag("officer");
                sender.displayClientMessage(Component.literal(player.getDisplayName() + " doesn't have any role now").withStyle(ChatFormatting.GREEN), false);
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

