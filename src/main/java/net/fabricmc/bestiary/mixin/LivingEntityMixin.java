package net.fabricmc.bestiary.mixin;

import net.fabricmc.bestiary.Bestiary;
import net.fabricmc.bestiary.components.MobKillsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();
        System.out.println("HERE");
        if (attacker instanceof PlayerEntity) {
            MobKillsComponent component = Bestiary.KILLS_COMPONENT.get(attacker);
            component.addKills((LivingEntity) (Object) this, 1);
        }

    }

}
