package net.fabricmc.bestiary.items;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import net.fabricmc.bestiary.gui.CategoriesBestiaryGUI;
import net.fabricmc.bestiary.gui.screens.CategoriesBestiaryScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BestiaryItem extends Item {

    private MinecraftClient client;

    public BestiaryItem(Settings settings) {
        super(settings);
        client = MinecraftClient.getInstance();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        LightweightGuiDescription categories = new CategoriesBestiaryGUI();

        this.client.openScreen(new CategoriesBestiaryScreen(categories));

        return new TypedActionResult(ActionResult.PASS, playerEntity.getStackInHand(hand));
    }
}
