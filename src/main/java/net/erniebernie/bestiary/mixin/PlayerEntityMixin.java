package net.erniebernie.bestiary.mixin;

import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.components.BestiaryComponent;
import net.erniebernie.bestiary.gui.models.BeastDetail;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    private BestiaryComponent component;
    private BeastDetail detail;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
        component = BestiaryMod.KILLS_COMPONENT.get(this);
        detail = BestiaryMod.BEAST_DETAILS.get("minecraft:iron_golem");
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(CallbackInfo ci) {
        // TODO : Add option to disable this and in fact all effects that are being granted to a player
        if (this.world.getTime() % 80L == 0L) {
            int golemProgress = component.getProgress(Registry.ENTITY_TYPE.get(new Identifier("minecraft:iron_golem")));
            if (golemProgress > detail.getLvl1Req()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 500, 0));
            } else if (golemProgress > detail.getLvl2Req()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 500, 1));
            }
        }
    }
}
