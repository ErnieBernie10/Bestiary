package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import net.fabricmc.bestiary.KillCounterModel;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
import net.fabricmc.bestiary.gui.widget.WCustomLabel;
import net.minecraft.entity.EntityType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigatableGUI extends BaseBestiaryGUI {

    private BaseBestiaryGUI parent;
    private WButton homeButton;
    private List<KillCounterModel> kills;

    public NavigatableGUI(Identifier background, BaseBestiaryGUI parent) {
        super(background);
        kills = new ArrayList<>();
        this.parent = parent;
        homeButton = new WButton(new LiteralText("Home"));
        homeButton.setOnClick(() -> openScreen(new BestiaryScreen(parent)));
        getRoot().add(homeButton, 0, 0, 2, 0);
    }

    public List<KillCounterModel> getKills() {
        return kills;
    }

    public void setKills(List<KillCounterModel> kills) {
        this.kills = kills;
    }
}
