package net.fabricmc.bestiary;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.bestiary.items.BestiaryItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Bestiary implements ModInitializer {
	// an instance of our new item
	public static final Item BESTIARY_ITEM = new BestiaryItem(new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("bestiary", "bestiary"), BESTIARY_ITEM);
	}

}
