package net.fabricmc.bestiary.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.util.Identifier;

public class WSpriteGridPanel extends WGridPanel {

    private Identifier sprite;
    private int spriteWidth;
    private int spriteHeight;

    public WSpriteGridPanel(Identifier sprite, int spriteWidth, int spriteHeight) {
        this.sprite = sprite;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    @Override
    public void paintBackground(int x, int y) { }

    @Override
    public void paintBackground(int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.rect(sprite, x, y, spriteWidth, spriteHeight, -1);
    }
}
