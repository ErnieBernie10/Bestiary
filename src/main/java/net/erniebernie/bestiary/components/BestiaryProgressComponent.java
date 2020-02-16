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

public class BestiaryProgressComponent implements BestiaryComponent {
    private Map<String, Integer> passiveProgress;
    private Map<String, Integer> neutralProgress;
    private Map<String, Integer> hostileProgress;
    private Map<String, Integer> bossProgress;

    private Identifier passivesId = new Identifier("fabric-bestiary:passives");
    private Identifier neutralsId = new Identifier("fabric-bestiary:neutrals");
    private Identifier hostilesId = new Identifier("fabric-bestiary:hostiles");
    private Identifier bossesId = new Identifier("fabric-bestiary:bosses");

    private PlayerEntity owner;

    public BestiaryProgressComponent(PlayerEntity owner) {
        passiveProgress = new HashMap<>();
        neutralProgress = new HashMap<>();
        hostileProgress = new HashMap<>();
        bossProgress = new HashMap<>();
        this.owner = owner;
    }

    public void addProgress(EntityType entityType, int amount) {
        addProgress(sortEntityType(entityType), entityType, amount);
    }

    private Map<String, Integer> sortEntityType(EntityType<?> type) {
        if (type.isTaggedWith(TagRegistry.entityType(passivesId))) {
            return this.passiveProgress;
        } else if (type.isTaggedWith(TagRegistry.entityType(neutralsId))) {
            return this.neutralProgress;
        } else if (type.isTaggedWith(TagRegistry.entityType(hostilesId))) {
            return this.hostileProgress;
        } else if (type.isTaggedWith(TagRegistry.entityType(bossesId))) {
            return this.bossProgress;
        }
        return null;
    }

    @Override
    public int getProgress(EntityType<?> type) {
        String typeId = getTypeId(type);
        return sortEntityType(type).getOrDefault(typeId, 0);
    }

    private String getTypeId(EntityType<?> type) {
        return Registry.ENTITY_TYPE.getId(type).toString();
    }

    private void addProgress(Map<String, Integer> map, EntityType type, int amount) {
        String typeId = getTypeId(type);
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
        this.passiveProgress = tagToMap(tag.get("passives"));
        this.neutralProgress = tagToMap(tag.get("neutrals"));
        this.hostileProgress = tagToMap(tag.get("hostiles"));
        this.bossProgress = tagToMap(tag.get("bosses"));
        System.out.println(this.toString());
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.put("passives", toTag(passiveProgress));
        tag.put("neutrals", toTag(neutralProgress));
        tag.put("hostiles", toTag(hostileProgress));
        tag.put("bosses", toTag(bossProgress));
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
        mapToString(passiveProgress, builder);
        mapToString(neutralProgress, builder);
        mapToString(hostileProgress, builder);
        mapToString(bossProgress, builder);
        return builder.toString();
    }

    private void mapToString(Map<String, Integer> map, StringBuilder builder) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
    }

    public Map<String, Integer> getPassiveProgress() {
        return passiveProgress;
    }

    public Map<String, Integer> getNeutralProgress() {
        return neutralProgress;
    }

    public Map<String, Integer> getHostileProgress() {
        return hostileProgress;
    }

    public Map<String, Integer> getBossProgress() {
        return bossProgress;
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
    public ComponentType<BestiaryComponent> getComponentType() {
        return BestiaryMod.KILLS_COMPONENT;
    }
}
