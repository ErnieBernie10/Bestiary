package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.fabricmc.bestiary.gui.widget.WSpriteButton;
import net.fabricmc.bestiary.gui.widget.WSpriteGridPanel;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class BaseBestiaryGUI extends LightweightGuiDescription {
    public BaseBestiaryGUI() {
        WSpriteGridPanel root = new WSpriteGridPanel(new Identifier("bestiary", "textures/gui/blank_page.png"), 320, 200);
        setRootPanel(root);
        root.setSize(320, 200);

        WSpriteButton button1 = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/passive_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/passive_selected_bookmark.png"),
                32,
                171);
        root.add(button1, 0, 5, 2, 10);

        WLabel label = new WLabel(new LiteralText("Test"), 0xFFFFFF);
        root.add(label, 0, 4, 2, 1);
    }
}
