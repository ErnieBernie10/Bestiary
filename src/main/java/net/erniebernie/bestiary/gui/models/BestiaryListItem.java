package net.erniebernie.bestiary.gui.models;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.erniebernie.bestiary.gui.DetailBestiaryGui;
import net.erniebernie.bestiary.gui.screens.BestiaryScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class BestiaryListItem extends WPlainPanel {

    private String id;
    private WLabel name;
    private WLabel kills;
    private WButton details;

    public BestiaryListItem() {
        this.name = new WLabel("");
        this.add(name, 1, 1);
        this.kills = new WLabel("");
        this.add(kills, 3, 1);
        this.details = new WButton(new LiteralText("Details"));
        this.details.setOnClick(() -> MinecraftClient.getInstance().openScreen(new BestiaryScreen(new DetailBestiaryGui(id))));
        this.add(details, 5, 1);
        this.id = "";
    }

    public void setName(String name) {
        this.name = new WLabel(name);
    }

    public void setKills(int kills) {
        this.kills = new WLabel(Integer.toString(kills));
    }

    public void setId(String id) {
        this.id = id;
    }
}
