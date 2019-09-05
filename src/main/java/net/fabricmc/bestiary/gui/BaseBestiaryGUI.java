package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
import net.fabricmc.bestiary.gui.widget.WSpriteGridPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public abstract class BaseBestiaryGUI extends LightweightGuiDescription {
    private WSpriteGridPanel root;
    private MinecraftClient client;

    public BaseBestiaryGUI() {
        this(Identifiers.BLACK_PAGE_BACKGROUND);
    }

    public BaseBestiaryGUI(Identifier background) {
        root = new WSpriteGridPanel(background, 340, 220);
        setRootPanel(root);
        root.setSize(340, 220);

        client = MinecraftClient.getInstance();
    }

    public WSpriteGridPanel getRoot() {
        return root;
    }

    public void openScreen(ClientCottonScreen screen) {
        client.openScreen(screen);
    }
}
