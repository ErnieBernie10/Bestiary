package net.erniebernie.bestiary.components;

import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.EntityType;

import java.util.Map;

public interface BestiaryComponent extends Component, EntitySyncedComponent {
    Map<String, Integer> getPassiveProgress();
    Map<String, Integer> getNeutralProgress();
    Map<String, Integer> getHostileProgress();
    Map<String, Integer> getBossProgress();
    void addProgress(EntityType type, int amount);
}
