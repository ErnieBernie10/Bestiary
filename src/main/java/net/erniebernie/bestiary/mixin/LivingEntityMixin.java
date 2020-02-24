package net.erniebernie.bestiary.mixin;

import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.LivingEntityExt;
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
public abstract class LivingEntityMixin extends Entity implements LivingEntityExt {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    private boolean researched = false;

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
        // TODO : Refactor everything to make a unified abilities handler
        if (source.getAttacker() instanceof PlayerEntity) {
            BestiaryComponent component = BestiaryMod.KILLS_COMPONENT.get(source.getAttacker());
            BeastDetail detail = BestiaryMod.BEAST_DETAILS.get(Registry.ENTITY_TYPE.getId(this.getType()).toString());
            float progress = component.getProgress(this.getType());
            if (source == DamageSource.FALL) {
                if (detail.getId().equals("minecraft:chicken")) {
                    float decrease = 0;
                    if (progress > detail.getLvl1Req()) {
                        decrease = 0.1f;
                    } else if (progress > detail.getLvl2Req()) {
                        decrease = 0.25f;
                    }
                    amount *= (1 - decrease);
                }
            }
            amount += progress / detail.getDamageModifier();
        }
        return amount;
    }

    @Override
    public void research() {
        this.researched = true;
    }

    @Override
    public boolean isResearched() {
        return this.researched;
    }
}

