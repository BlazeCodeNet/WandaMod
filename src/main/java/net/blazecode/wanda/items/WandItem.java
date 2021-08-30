package net.blazecode.wanda.items;

import net.blazecode.vanillify.api.VanillaUtils;
import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.blazecode.wanda.WandaMod;
import net.blazecode.wanda.api.WandaAPI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class WandItem extends Item implements ItemStackProxy
{
    public WandItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {

        if(!context.getWorld().getBlockState(context.getBlockPos()).isAir())
        {
            WandaAPI.setPosTwo( context.getBlockPos(), (ServerPlayerEntity)context.getPlayer() );
        }

        return super.useOnBlock(context);
    }

    @Override
    public ItemStack getClientItemStack(ItemStack itemStack)
    {
        ItemStack tmp = new ItemStack(Items.NETHER_STAR, 1);

        tmp.setCustomName(VanillaUtils.getText("Selection Wand", Formatting.BOLD, Formatting.GOLD));

        List<Text> loreList = new ArrayList<>();
        loreList.add(VanillaUtils.getText("Left-Click to Set Pos1", Formatting.DARK_AQUA));
        loreList.add(VanillaUtils.getText("Right-Click to Set Pos2", Formatting.YELLOW));
        loreList.add(VanillaUtils.getText("Press F to Clear Selection", Formatting.RED));
        VanillaUtils.setStackLore(tmp, loreList);

        return tmp;
    }

    @Override
    public Identifier getIdentifier()
    {
        return new Identifier(WandaMod.MODID, "wand");
    }
}
