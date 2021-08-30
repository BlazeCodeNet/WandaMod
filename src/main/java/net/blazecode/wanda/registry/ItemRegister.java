package net.blazecode.wanda.registry;

import net.blazecode.wanda.items.WandItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.registry.Registry;

public class ItemRegister
{
    public static void init()
    {
        Registry.register(Registry.ITEM, WAND_ITEM.getIdentifier(), WAND_ITEM);
    }

    public static final WandItem WAND_ITEM = new WandItem(new FabricItemSettings().maxCount(1));
}
