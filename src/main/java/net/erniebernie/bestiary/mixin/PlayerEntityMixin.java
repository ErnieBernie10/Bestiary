package net.erniebernie.bestiary.mixin;

import net.erniebernie.bestiary.KillCounter;
import net.erniebernie.bestiary.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityExt {

    private KillCounter kills = new KillCounter();

    @Override
    public void addKills(EntityType entity) {
        kills.addKills(entity, 1);
    }

    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.put("kills", kills.toTag());
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        kills = new KillCounter();
        kills.fromTag(tag);
    }
}
