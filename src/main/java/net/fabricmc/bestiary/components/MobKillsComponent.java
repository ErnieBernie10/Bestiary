package net.fabricmc.bestiary.components;


import nerdhub.cardinal.components.api.component.Component;

public interface MobKillsComponent extends Component {
    int getKills();
    void addKills(int amount);
}
