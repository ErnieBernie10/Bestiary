package net.erniebernie.bestiary.items;

import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.components.CounterComponent;
import net.erniebernie.bestiary.gui.MainBestiaryGui;
import net.erniebernie.bestiary.gui.screens.BestiaryScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BestiaryBookItem extends Item {
    public BestiaryBookItem(Settings group) {
        super(group);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            CounterComponent counter = BestiaryMod.KILLS_COMPONENT.get(user);
            MinecraftClient.getInstance().openScreen(new BestiaryScreen(new MainBestiaryGui(counter)));
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, (hand==Hand.MAIN_HAND) ? user.getMainHandStack() : user.getOffHandStack());
    }
}
