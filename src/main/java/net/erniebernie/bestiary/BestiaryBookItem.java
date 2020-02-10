package net.erniebernie.bestiary;

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
            KillCounter counter = ((PlayerEntityExt) user).getKillCounter();
            MinecraftClient.getInstance().openScreen(new BestiaryScreen(new MainBestiaryGui(counter)));
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, (hand==Hand.MAIN_HAND) ? user.getMainHandStack() : user.getOffHandStack());
    }
}
