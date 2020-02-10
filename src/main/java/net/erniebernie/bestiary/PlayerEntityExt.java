package net.erniebernie.bestiary;

import net.minecraft.entity.EntityType;

public interface PlayerEntityExt {
    void addKills(EntityType entity);
    KillCounter getKillCounter();
    void setKillCounter(KillCounter counter);
}
