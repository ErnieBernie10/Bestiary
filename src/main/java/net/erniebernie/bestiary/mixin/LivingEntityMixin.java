package net.erniebernie.bestiary.mixin;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.gui.components.CounterComponent;
import net.erniebernie.bestiary.gui.components.KillCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo info) {
        if (source.getAttacker() instanceof PlayerEntity) {
            CounterComponent counter = BestiaryMod.KILLS_COMPONENT.get(source.getAttacker());
            counter.addKills(this.getType(), 1);
        }
    }
}
