package net.fabricmc.bestiary.items;

import net.fabricmc.bestiary.Bestiary;
import net.fabricmc.bestiary.components.MobKillsComponent;
import net.fabricmc.bestiary.screens.BestiaryScreen;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.container.ContainerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.minecraft.client.util.NarratorManager.EMPTY;

public class BestiaryItem extends Item {

    private MinecraftClient client;

    public BestiaryItem(Settings settings) {
        super(settings);
        client = MinecraftClient.getInstance();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        Screen s = new BestiaryScreen(new LiteralText("Test"));
        this.client.openScreen(s);
        MobKillsComponent component = Bestiary.KILLS_COMPONENT.get(playerEntity);
        component.addKills(1);
        System.out.println(component.getKills());
        return new TypedActionResult(ActionResult.PASS, playerEntity.getStackInHand(hand));
    }
}
