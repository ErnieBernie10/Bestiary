package net.erniebernie.bestiary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.erniebernie.bestiary.components.BestiaryComponent;
import net.erniebernie.bestiary.components.BestiaryProgressComponent;
import net.erniebernie.bestiary.gui.models.BeastDetail;
import net.erniebernie.bestiary.items.BestiaryBookItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

public class BestiaryMod implements ModInitializer {

    public static final Item BESTIARY_BOOK_ITEM = new BestiaryBookItem(new Item.Settings().group(ItemGroup.MISC));
    public static final ComponentType<BestiaryComponent> KILLS_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("fabric-bestiary:kills"), BestiaryComponent.class);
    public static final AbilitySource BAT_FLIGHT = Pal.getAbilitySource("fabric-bestiary", "bat-flight");
    public static Map<String, BeastDetail> BEAST_DETAILS = null;

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("fabric-bestiary", "bestiary_book"), BESTIARY_BOOK_ITEM);
        EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(KILLS_COMPONENT, new BestiaryProgressComponent(player)));
        EntityComponents.setRespawnCopyStrategy(KILLS_COMPONENT, RespawnCopyStrategy.ALWAYS_COPY);

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("fabric-bestiary", "book-content-reload-listener");
            }

            @Override
            public void apply(ResourceManager manager) {
                try (InputStream stream = manager.getResource(new Identifier("fabric-bestiary", "book-content.json")).getInputStream()) {
                    Gson gson = new Gson();
                    Type beastDetailMapType = new TypeToken<Map<String, BeastDetail>>() { }.getType();
                    BEAST_DETAILS = gson.fromJson(new JsonReader(new InputStreamReader(stream)), beastDetailMapType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
