package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
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
    private Map<EntityType, Integer> types;

    public NavigatableGUI(Identifier background, BaseBestiaryGUI parent) {
        super(background);
        types = new HashMap<>();
        this.parent = parent;
        homeButton = new WButton(new LiteralText("Home"));
        homeButton.setOnClick(() -> openScreen(new BestiaryScreen(parent)));
        getRoot().add(homeButton, 0, 0, 2, 0);

    }

    public Map<EntityType, Integer> getTypes() {
        return types;
    }

    public void setTypes(Map<EntityType, Integer> types) {
        this.types = types;
        int pos = 0;
        for (Map.Entry<EntityType, Integer> entry : types.entrySet()) {
            getRoot().add(new WLabel("TEST"), 5, pos, 5, 1);
            pos += 2;
        }
    }
}