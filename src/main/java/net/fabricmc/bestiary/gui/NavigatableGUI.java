package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class NavigatableGUI extends BaseBestiaryGUI {

    private BaseBestiaryGUI parent;
    private WButton homeButton;

    public NavigatableGUI(Identifier background, BaseBestiaryGUI parent) {
        super(background);
        this.parent = parent;
        homeButton = new WButton(new LiteralText("Home"));
        homeButton.setOnClick(() -> openScreen(new BestiaryScreen(parent)));
        getRoot().add(homeButton, 0, 0, 2, 0);
    }
}
