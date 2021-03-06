package net.blazecode.wanda.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.blazecode.vanillify.api.VanillaUtils;
import net.blazecode.wanda.api.WandaAPI;
import net.blazecode.wanda.registry.ItemRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class WandCommand
{
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("wand")
                .requires( src -> Permissions.require("wanda.command.wand").test(src) || src.hasPermissionLevel(2))
                    .executes(WandCommand::execute);

        dispatcher.register(builder);
    }

    public static int execute(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException
    {
        ServerPlayerEntity srvPlr = ctx.getSource().getPlayer();

        if(srvPlr != null)
        {
            srvPlr.getInventory().insertStack(new ItemStack(ItemRegister.WAND_ITEM, 1));
            srvPlr.sendMessage(WandaAPI.getPrefixText().append(VanillaUtils.getText("Gave you a wand!", Formatting.GREEN)), false);
        }

        return Command.SINGLE_SUCCESS;
    }
}
