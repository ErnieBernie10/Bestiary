package net.erniebernie.bestiary;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.erniebernie.bestiary.components.BestiaryComponent;
import net.erniebernie.bestiary.components.BestiaryProgressComponent;
import net.erniebernie.bestiary.items.BestiaryBookItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BestiaryMod implements ModInitializer {

    public static final Item BESTIARY_BOOK_ITEM = new BestiaryBookItem(new Item.Settings().group(ItemGroup.MISC));
    public BestiaryConfig config;
    public static final ComponentType<BestiaryComponent> KILLS_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("fabric-bestiary:kills"), BestiaryComponent.class);

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("fabric-bestiary", "bestiary_book"), BESTIARY_BOOK_ITEM);
        config = new BestiaryConfig("path");
        // Add the component to every instance of PlayerEntity
        EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(KILLS_COMPONENT, new BestiaryProgressComponent(player)));
        // Ensure the component's data is copied when keepInventory is enabled (Optional)
        EntityComponents.setRespawnCopyStrategy(KILLS_COMPONENT, RespawnCopyStrategy.INVENTORY);
    }
}
