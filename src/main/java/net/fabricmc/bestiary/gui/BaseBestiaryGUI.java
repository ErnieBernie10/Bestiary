package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import net.fabricmc.bestiary.gui.widget.WSpriteGridPanel;
import net.minecraft.util.Identifier;

public class BaseBestiaryGUI extends LightweightGuiDescription {
    private WSpriteGridPanel root;

    public BaseBestiaryGUI() {
        root = new WSpriteGridPanel(new Identifier("bestiary", "textures/gui/blank_page.png"), 340, 220);
        setRootPanel(root);
        root.setSize(340, 220);
    }

    public WSpriteGridPanel getRoot() {
        return root;
    }
}
