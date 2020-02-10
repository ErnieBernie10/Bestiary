package net.erniebernie.bestiary.gui.models;

import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WWidget;

public class BestiaryListItem extends WWidget {

    private WLabel name;
    private WLabel kills;

    public BestiaryListItem() {
        this.name = new WLabel("");
        this.kills = new WLabel("");
    }

    public void setName(String name) {
        this.name = new WLabel(name);
    }

    public void setKills(int kills) {
        this.kills = new WLabel(Integer.toString(kills));
    }
}
