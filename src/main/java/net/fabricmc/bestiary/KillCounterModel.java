package net.fabricmc.bestiary;

import net.minecraft.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KillCounterModel {
    EntityType type;
    int kills;

    public KillCounterModel(EntityType type, int kills) {
        this.type = type;
        this.kills = kills;
    }

    public EntityType getType() {
        return type;
    }

    public int getKills() {
        return kills;
    }

    public static List<KillCounterModel> createModelList(Map<EntityType, Integer> kills) {
        List<KillCounterModel> listModel = new ArrayList<>();
        for (Map.Entry<EntityType, Integer> entry : kills.entrySet()) {
            listModel.add(new KillCounterModel(entry.getKey(), entry.getValue()));
        }
        return listModel;
    }
}
