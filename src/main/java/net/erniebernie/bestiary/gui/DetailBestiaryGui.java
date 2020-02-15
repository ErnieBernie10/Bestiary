package net.erniebernie.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import net.erniebernie.bestiary.gui.models.BeastDetail;

public class DetailBestiaryGui extends LightweightGuiDescription {

    private String id;
    private BeastDetail detail;

    public DetailBestiaryGui(String id, BeastDetail detail) {
        this.id = id;
        this.detail = detail;
    }

    public DetailBestiaryGui(String id) {
        this.id = id;
    }
}
