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
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BestiaryMod implements ModInitializer {

    public static final Item BESTIARY_BOOK_ITEM = new BestiaryBookItem(new Item.Settings().group(ItemGroup.MISC));
    public BestiaryConfig config;
    public static final ComponentType<BestiaryComponent> KILLS_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("fabric-bestiary:kills"), BestiaryComponent.class);
    public static final AbilitySource BAT_FLIGHT = Pal.getAbilitySource("fabric-bestiary", "bat-flight");
    private static final Type BEAST_DETAIL_TYPE = new TypeToken<List<BeastDetail>>() { }.getType();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("fabric-bestiary", "bestiary_book"), BESTIARY_BOOK_ITEM);
        // Add the component to every instance of PlayerEntity
        EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(KILLS_COMPONENT, new BestiaryProgressComponent(player)));
        // Ensure the component's data is copied when keepInventory is enabled (Optional)
        EntityComponents.setRespawnCopyStrategy(KILLS_COMPONENT, RespawnCopyStrategy.ALWAYS_COPY);

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new IdentifiableResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("fabric-bestiary", "book-content.json");
            }

            @Override
            public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
                return CompletableFuture.runAsync(() -> {
                    try(InputStream stream = manager.getResource(getFabricId()).getInputStream()) {
                        Gson gson = new Gson();
                        List<BeastDetail> details = gson.fromJson(new JsonReader(new InputStreamReader(stream)), BEAST_DETAIL_TYPE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
