package net.fabricmc.bestiary.components;


import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import java.util.Map;

public interface MobKillsComponent extends Component {
    int getKills(LivingEntity entity);
    void addKills(LivingEntity entity, int amount);
    Map<EntityType, Integer> getPassiveKills();
    Map<EntityType, Integer> getNeutralKills();
    Map<EntityType, Integer> getHostileKills();
    Map<EntityType, Integer> getBossKills();
}
