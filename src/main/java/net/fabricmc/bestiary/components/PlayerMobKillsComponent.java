package net.fabricmc.bestiary.components;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;


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
        String mobId = Registry.ENTITY_TYPE.getId(entity.getType()).toString();
        kills.putInt(mobId, kills.getInt(mobId) + amount);
    }

    public Map<EntityType, Integer> getHostileKills() {
        return null;
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
