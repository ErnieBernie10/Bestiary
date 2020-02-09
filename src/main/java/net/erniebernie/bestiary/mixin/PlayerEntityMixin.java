package net.erniebernie.bestiary.mixin;

import net.erniebernie.bestiary.KillCounter;
import net.erniebernie.bestiary.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityExt {

    private KillCounter kills;

    public PlayerEntityMixin() {
        kills = new KillCounter();
    }

    @Override
    public void addKills(EntityType entity) {
        kills.addKills(entity, 1);
    }
}
