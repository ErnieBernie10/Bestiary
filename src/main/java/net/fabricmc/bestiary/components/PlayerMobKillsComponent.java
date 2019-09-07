package net.fabricmc.bestiary.components;

import net.fabricmc.bestiary.Bestiary;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PlayerMobKillsComponent implements MobKillsComponent {
    private CompoundTag kills;
    private PlayerEntity owner;
    private Map<EntityType, Integer> passiveKills;
    private Map<EntityType, Integer> neutralKills;
    private Map<EntityType, Integer> hostileKills;
    private Map<EntityType, Integer> bossKills;
    private static Identifier passiveIdentifier = new Identifier("bestiary:passives");
    private static Identifier neutralIdentifier = new Identifier("bestiary:neutrals");
    private static Identifier bossIdentifier = new Identifier("bestiary:bosses");
    private static Identifier hostileIdentifier = new Identifier("bestiary:hostiles");

    public PlayerMobKillsComponent(PlayerEntity owner) {
        kills = new CompoundTag();
        passiveKills = new HashMap<>();
        neutralKills = new HashMap<>();
        hostileKills = new HashMap<>();
        bossKills = new HashMap<>();
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
        sortType(mobId, kills.getInt(mobId) + amount);
        kills.putInt(mobId, kills.getInt(mobId) + amount);
    }

    @Override
    public Map<EntityType, Integer> getPassiveKills() {
        return passiveKills;
    }

    @Override
    public Map<EntityType, Integer> getNeutralKills() {
        return neutralKills;
    }

    @Override
    public Map<EntityType, Integer> getHostileKills() {
        return hostileKills;
    }

    @Override
    public Map<EntityType, Integer> getBossKills() {
        return bossKills;
    }

    private boolean isOfCaterory(EntityType mob, Identifier identifier) {
        return TagRegistry.entityType(identifier).contains(mob);
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        kills = compoundTag;
        Set<String> keys = kills.getKeys();
        for (String key : keys) {
            sortType(key, kills.getInt(key));
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        compoundTag = kills;
        return compoundTag;
    }

    private void sortType(String typeId, int amount) {
        if (typeId.startsWith("minecraft:")) {
            EntityType mob = Registry.ENTITY_TYPE.get(new Identifier(typeId));
            if (isOfCaterory(mob, passiveIdentifier)) {
                passiveKills.put(mob, amount);
            } else if (isOfCaterory(mob, neutralIdentifier)) {
                neutralKills.put(mob, amount);
            } else if (isOfCaterory(mob, hostileIdentifier)) {
                hostileKills.put(mob, amount);
            } else if (isOfCaterory(mob, bossIdentifier)) {
                bossKills.put(mob, amount);
            }
        }
    }
}
