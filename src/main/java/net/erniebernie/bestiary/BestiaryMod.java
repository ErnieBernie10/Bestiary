package net.erniebernie.bestiary;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BestiaryMod implements ModInitializer {

    public static final Item FABRIC_ITEM = new BestiaryBookItem(new Item.Settings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("fabric-bestiary", "bestiary_book"), FABRIC_ITEM);
    }
}
