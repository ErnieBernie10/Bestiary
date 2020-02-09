package net.erniebernie.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.erniebernie.bestiary.gui.widgets.WSpriteButton;
import net.minecraft.util.Identifier;

public class MainBestiaryGui extends LightweightGuiDescription {

    public MainBestiaryGui() {
        WGridPanel root = new WGridPanel();
        root.setSize(320, 200);
        setRootPanel(root);

        WSpriteButton btnPassives = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/passive_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/passive_selected_bookmark.png"));
        btnPassives.setOnClick(() -> {

        });
        btnPassives.setSize(32, 171);
        root.add(btnPassives, 10, 0);

        WSpriteButton btnNeutrals = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/neutral_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/neutral_selected_bookmark.png"));
        btnNeutrals.setOnClick(() -> {

        });
        btnNeutrals.setSize(32, 171);
        root.add(btnNeutrals, 12, 0);

        WSpriteButton btnHostiles = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/hostile_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/hostile_selected_bookmark.png"));
        btnHostiles.setOnClick(() -> {

        });
        btnHostiles.setSize(32, 171);
        root.add(btnHostiles, 14, 0);

        WSpriteButton btnBosses = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/boss_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/boss_selected_bookmark.png"));
        btnBosses.setOnClick(() -> {

        });
        btnBosses.setSize(32, 171);
        root.add(btnBosses, 16, 0);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter((i, i1, wWidget) -> ScreenDrawing.texturedRect(i, i1, wWidget.getWidth(), wWidget.getHeight(), new Identifier("fabric-bestiary", "textures/gui/blank_page.png"), -1));
    }
}
