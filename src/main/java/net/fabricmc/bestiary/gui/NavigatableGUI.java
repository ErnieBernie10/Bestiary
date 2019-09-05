package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
import net.minecraft.entity.EntityType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class NavigatableGUI extends BaseBestiaryGUI {

    private BaseBestiaryGUI parent;
    private WButton homeButton;
    private List<EntityType> types;

    public NavigatableGUI(Identifier background, BaseBestiaryGUI parent) {
        super(background);
        types = new ArrayList<>();
        this.parent = parent;
        homeButton = new WButton(new LiteralText("Home"));
        homeButton.setOnClick(() -> openScreen(new BestiaryScreen(parent)));
        getRoot().add(homeButton, 0, 0, 2, 0);

    }

    public List<EntityType> getTypes() {
        return types;
    }

    public void setTypes(List<EntityType> types) {
        this.types = types;
    }
}
