package net.erniebernie.bestiary.mixin;

import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.components.BestiaryComponent;
import net.erniebernie.bestiary.gui.models.BeastDetail;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo info) {
        if (source.getAttacker() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source.getAttacker();
            if (!player.getEntityWorld().isClient) {
                BestiaryComponent counter = BestiaryMod.KILLS_COMPONENT.get(player);
                counter.addProgress(this.getType(), 1);
                counter.sync();
            }
        }
    }

    @ModifyVariable(method = "applyDamage", at = @At("HEAD"))
    public float applyDamage(float amount, DamageSource source) {
        return applyBestiaryToDamage(source, amount);
    }

    public float applyBestiaryToDamage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) {
            float progress = BestiaryMod.KILLS_COMPONENT.get(source.getAttacker()).getProgress(this.getType());
            BeastDetail detail = BestiaryMod.BEAST_DETAILS.get(Registry.ENTITY_TYPE.getId(this.getType()).toString());
            return amount + progress / detail.getDamageModifier();
        }
        return amount;
    }
}

