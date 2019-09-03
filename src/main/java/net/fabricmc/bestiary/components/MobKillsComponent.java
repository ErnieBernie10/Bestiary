package net.fabricmc.bestiary.components;


import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.entity.LivingEntity;

public interface MobKillsComponent extends Component {
    int getKills(LivingEntity entity);
    void addKills(LivingEntity entity, int amount);
}
