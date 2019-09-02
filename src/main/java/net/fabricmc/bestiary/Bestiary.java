package net.fabricmc.bestiary;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.ComponentProvider;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.bestiary.components.MobKillsComponent;
import net.fabricmc.bestiary.components.PlayerMobKillsComponent;
import net.fabricmc.bestiary.items.BestiaryItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Bestiary implements ModInitializer {
	public static final Item BESTIARY_ITEM = new BestiaryItem(new Item.Settings().group(ItemGroup.MISC));
	public static final ComponentType<MobKillsComponent> KILLS_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("bestiary:killscomponent"), MobKillsComponent.class);

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("bestiary", "bestiary"), BESTIARY_ITEM);
		// Add the component to every instance of PlayerEntity
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(KILLS_COMPONENT, new PlayerMobKillsComponent(player)));
	}

}
