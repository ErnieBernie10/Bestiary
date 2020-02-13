package net.erniebernie.bestiary.gui.components;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.entity.EntityType;

import java.util.Map;

public interface CounterComponent extends Component {
    Map<String, Integer> getPassiveKills();
    Map<String, Integer> getNeutralKills();
    Map<String, Integer> getHostileKills();
    Map<String, Integer> getBossKills();
    void addKills(EntityType type, int amount);
}
