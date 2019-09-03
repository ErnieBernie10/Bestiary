package net.fabricmc.bestiary.components;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;


public class PlayerMobKillsComponent implements MobKillsComponent {
    private CompoundTag kills;
    private PlayerEntity owner;

    public PlayerMobKillsComponent(PlayerEntity owner) {
        kills = new CompoundTag();
        this.owner = owner;
    }

    public PlayerEntity getOwner() {
        return owner;
    }

    @Override
    public int getKills(LivingEntity entity) {
        return kills.getInt(entity.getName().asString());
    }

    @Override
    public void addKills(LivingEntity entity, int amount) {
        String mobName = entity.getName().asString();
        kills.putInt(mobName, kills.getInt(mobName) + amount);
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        kills = compoundTag;
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        return this.kills;
    }
}
