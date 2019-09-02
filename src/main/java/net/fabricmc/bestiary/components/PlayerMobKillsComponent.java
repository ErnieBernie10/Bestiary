package net.fabricmc.bestiary.components;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public class PlayerMobKillsComponent implements MobKillsComponent {
    private int kills;
    private PlayerEntity owner;

    public PlayerMobKillsComponent(PlayerEntity owner) {
        kills = 0;
        this.owner = owner;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public void addKills(int amount) {
        kills += amount;
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        kills = compoundTag.getInt("kills");
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        compoundTag.putInt("kills", kills);
        return compoundTag;
    }
}
