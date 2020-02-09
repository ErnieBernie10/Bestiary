package net.erniebernie.bestiary;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class KillCounter {
    private Map<String, Integer> passiveKills;
    private Map<String, Integer> neutralKills;
    private Map<String, Integer> hostileKills;
    private Map<String, Integer> bossKills;

    private Identifier passivesId = new Identifier("fabric-bestiary:passives");
    private Identifier neutralsId = new Identifier("fabric-bestiary:neutrals");
    private Identifier hostilesId = new Identifier("fabric-bestiary:hostiles");
    private Identifier bossesId = new Identifier("fabric-bestiary:bosses");

    public KillCounter() {
        passiveKills = new HashMap<>();
        neutralKills = new HashMap<>();
        hostileKills = new HashMap<>();
        bossKills = new HashMap<>();
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

    public Tag toTag() {
        CompoundTag tag = new CompoundTag();
        Tag passives = toTag(passiveKills);
        Tag neutrals = toTag(neutralKills);
        Tag hostiles = toTag(hostileKills);
        Tag bosses = toTag(bossKills);
        tag.put("passives", passives);
        tag.put("neutrals", neutrals);
        tag.put("hostiles", hostiles);
        tag.put("bosses", bosses);
        return tag;
    }

    private Tag toTag(Map<String, Integer> map) {
        CompoundTag tag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            tag.putInt(entry.getKey(), entry.getValue());
        }
        return tag;
    }

    public void fromTag(CompoundTag tag) {
        this.passiveKills = tagToMap(tag.getCompound("passives"));
        this.neutralKills = tagToMap(tag.getCompound("neutrals"));
        this.hostileKills = tagToMap(tag.getCompound("hostiles"));
        this.bossKills = tagToMap(tag.getCompound("bosses"));
    }

    private Map<String, Integer> tagToMap(CompoundTag tag) {
        Map<String, Integer> kills = new HashMap<>();
        for (String key : tag.getKeys()) {
            kills.put(key, tag.getInt(key));
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
}
