package net.erniebernie.bestiary.components;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.Component;
import net.erniebernie.bestiary.BestiaryMod;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class KillCounter implements CounterComponent {
    private Map<String, Integer> passiveKills;
    private Map<String, Integer> neutralKills;
    private Map<String, Integer> hostileKills;
    private Map<String, Integer> bossKills;

    private Identifier passivesId = new Identifier("fabric-bestiary:passives");
    private Identifier neutralsId = new Identifier("fabric-bestiary:neutrals");
    private Identifier hostilesId = new Identifier("fabric-bestiary:hostiles");
    private Identifier bossesId = new Identifier("fabric-bestiary:bosses");

    private PlayerEntity owner;

    public KillCounter(PlayerEntity owner) {
        passiveKills = new HashMap<>();
        neutralKills = new HashMap<>();
        hostileKills = new HashMap<>();
        bossKills = new HashMap<>();
        this.owner = owner;
    }

    public void addKills(EntityType entityType, int amount) {
        if (entityType.isTaggedWith(TagRegistry.entityType(passivesId))) {
            addKills(passiveKills, entityType, amount);
        } else if (entityType.isTaggedWith(TagRegistry.entityType(neutralsId))) {
            addKills(neutralKills, entityType, amount);
        } else if (entityType.isTaggedWith(TagRegistry.entityType(hostilesId))) {
            addKills(hostileKills, entityType, amount);
        } else if (entityType.isTaggedWith(TagRegistry.entityType(bossesId))) {
            addKills(bossKills, entityType, amount);
        }
    }

    private void addKills(Map<String, Integer> map, EntityType type, int amount) {
        String typeId = Registry.ENTITY_TYPE.getId(type).toString();
        if (!map.containsKey(typeId)) {
            map.put(typeId, amount);
        } else {
            map.put(typeId, map.get(typeId) + amount);
        }
    }

    private CompoundTag toTag(Map<String, Integer> map) {
        CompoundTag tag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            tag.putInt(entry.getKey(), entry.getValue());
        }
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.passiveKills = tagToMap(tag.get("passives"));
        this.neutralKills = tagToMap(tag.get("neutrals"));
        this.hostileKills = tagToMap(tag.get("hostiles"));
        this.bossKills = tagToMap(tag.get("bosses"));
        System.out.println(this.toString());
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.put("passives", toTag(passiveKills));
        tag.put("neutrals", toTag(neutralKills));
        tag.put("hostiles", toTag(hostileKills));
        tag.put("bosses", toTag(bossKills));
        System.out.println(this.toString());
        return tag;
    }

    @Override
    public boolean isComponentEqual(Component other) {
        return false;
    }

    private Map<String, Integer> tagToMap(Tag tag) {
        Map<String, Integer> kills = new HashMap<>();
        CompoundTag cTag = (CompoundTag) tag;
        for (String key : cTag.getKeys()) {
            kills.put(key, cTag.getInt(key));
        }
        return kills;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        mapToString(passiveKills, builder);
        mapToString(neutralKills, builder);
        mapToString(hostileKills, builder);
        mapToString(bossKills, builder);
        return builder.toString();
    }

    private void mapToString(Map<String, Integer> map, StringBuilder builder) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
    }

    public Map<String, Integer> getPassiveKills() {
        return passiveKills;
    }

    public Map<String, Integer> getNeutralKills() {
        return neutralKills;
    }

    public Map<String, Integer> getHostileKills() {
        return hostileKills;
    }

    public Map<String, Integer> getBossKills() {
        return bossKills;
    }

    @Override
    public Entity getEntity() {
        return owner;
    }

    @Override
    public void sync() {
        if (!this.getEntity().world.isClient) {
            // We only sync with the holder, not with everyone around
            this.syncWith((ServerPlayerEntity) this.getEntity());
        }
    }

    @Override
    public ComponentType<CounterComponent> getComponentType() {
        return BestiaryMod.KILLS_COMPONENT;
    }
}
