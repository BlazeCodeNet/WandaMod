package net.blazecode.wanda.api;

import net.blazecode.vanillify.api.VanillaUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.UUID;

public class WandaAPI
{
    public static void setPosOne(BlockPos pos, ServerPlayerEntity player)
    {
        UUID plrID = player.getUuid();
        if(selectionOneMap.containsKey(plrID))
        {
            selectionOneMap.replace(plrID, pos);
        }
        player.sendMessage(getPrefixText().append(VanillaUtils.getText("Position 1 set to " + pos.toString())), false);
    }
    public static void setPosTwo(BlockPos pos, ServerPlayerEntity player)
    {
        UUID plrID = player.getUuid();
        if(selectionTwoMap.containsKey(plrID))
        {
            selectionTwoMap.replace(plrID, pos);
        }
        player.sendMessage(getPrefixText().append(VanillaUtils.getText("Position 2 set to " + pos.toString())), false);
    }

    public static BlockPos getPosOne(ServerPlayerEntity player)
    {
        return selectionOneMap.get(player.getUuid());
    }
    public static BlockPos getPosTwo(ServerPlayerEntity player)
    {
        return selectionTwoMap.get(player.getUuid());
    }

    public static MutableText getPrefixText()
    {
        return VanillaUtils.getText("[", Formatting.GOLD).append(VanillaUtils.getText("Wanda", Formatting.AQUA).append(VanillaUtils.getText("]", Formatting.GOLD)));
    }

    private static final HashMap<UUID, BlockPos> selectionOneMap = new HashMap<>();
    private static final HashMap<UUID, BlockPos> selectionTwoMap = new HashMap<>();
}
