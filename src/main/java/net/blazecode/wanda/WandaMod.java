package net.blazecode.wanda;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.blazecode.wanda.api.WandaAPI;
import net.blazecode.wanda.commands.WandCommand;
import net.blazecode.wanda.items.WandItem;
import net.blazecode.wanda.registry.ItemRegister;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.GameMode;

@Environment( EnvType.SERVER )
public class WandaMod implements DedicatedServerModInitializer
{

	public static final String MODID = "wanda";

	@Override
	public void onInitializeServer( )
	{
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

		ItemRegister.init();

		CommandRegistrationCallback.EVENT.register( ((dispatcher, dedicated) ->
		{
			if(dedicated)
			{
				WandCommand.register(dispatcher);
			}
		}));

		ServerPlayConnectionEvents.DISCONNECT.register( ((handler, server) ->
		{
			WandaAPI.clearPlayer(handler.getPlayer().getUuid());
		}) );

		AttackBlockCallback.EVENT.register( ( (player, world, hand, pos, direction) ->
		{
			ServerPlayerEntity srvPlr = (ServerPlayerEntity) player;
			if(srvPlr.interactionManager.getGameMode() != GameMode.SPECTATOR)
			{
				boolean primaryHand = hand.equals(Hand.MAIN_HAND);
				ItemStack stack;

				if(primaryHand)
				{
					stack = player.getMainHandStack();
				}
				else
				{
					stack = player.getOffHandStack();
				}

				if(stack.getItem() instanceof WandItem)
				{
					WandaAPI.setPosOne(pos, (ServerPlayerEntity)player);
					return ActionResult.FAIL;
				}
			}

			return ActionResult.PASS;
		}) );
	}

	public static ModConfig getConfig()
	{
		if (config == null)
		{
			config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		}
		return config;
	}

	private static ModConfig config;

	@Config(name = MODID)
	public static class ModConfig implements ConfigData
	{
		@Comment("Toggles the entire mod on or off")
		boolean enabled = true;
	}
}
