package net.erniebernie.bestiary.items;

import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.components.BestiaryComponent;
import net.erniebernie.bestiary.gui.MainBestiaryGui;
import net.erniebernie.bestiary.gui.screens.BestiaryScreen;
import net.erniebernie.bestiary.LivingEntityExt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
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

    private boolean usedOnEntity = false;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!usedOnEntity) {
            if (world.isClient) {
                BestiaryComponent counter = BestiaryMod.KILLS_COMPONENT.get(user);
                MinecraftClient.getInstance().openScreen(new BestiaryScreen(new MainBestiaryGui(counter)));
            }
        }
        usedOnEntity = false;
        return new TypedActionResult<>(ActionResult.SUCCESS, (hand==Hand.MAIN_HAND) ? user.getMainHandStack() : user.getOffHandStack());
    }

    @Override
    public boolean useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        usedOnEntity = true;
        LivingEntityExt ext = (LivingEntityExt) entity;
        if (!ext.isResearched()) {
            BestiaryComponent counter = BestiaryMod.KILLS_COMPONENT.get(user);
            counter.addProgress(entity.getType(), 10);
            ext.research();
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
