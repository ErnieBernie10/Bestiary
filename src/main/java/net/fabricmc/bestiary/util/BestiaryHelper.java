package net.fabricmc.bestiary.util;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.util.Identifier;

public class BestiaryHelper {
    public static String determineEntityCategory(Entity entity) {
        boolean isBoss = TagRegistry.entityType(new Identifier("bestiary:bosses")).contains(entity.getType());
        boolean isNeutral = TagRegistry.entityType(new Identifier("bestiary:neutrals")).contains(entity.getType());
        if (isBoss) {
            return "boss";
        } else if (isNeutral) {
            return "neutral";
        }else if (entity instanceof HostileEntity) {
            return "hostile";
        } else if (entity instanceof PassiveEntity) {
            return "passive";
        } else {
            return "misc";
        }
    }
}
