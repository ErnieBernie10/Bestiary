package net.fabricmc.bestiary.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.util.Identifier;

public class WSpriteButton extends WButton {

    private Identifier sprite;
    private Identifier spriteHovered;
    private int spriteWidth;
    private int spriteHeight;

    public WSpriteButton(Identifier sprite, Identifier spriteHovered, int spriteWidth, int spriteHeight) {
        this.sprite = sprite;
        this.spriteHovered = spriteHovered;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        super.height = spriteHeight;
        super.width = spriteWidth;
        super.setSize(spriteHeight, spriteWidth);
    }

    @Override
    public void paintForeground(int x, int y, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + this.getWidth() && mouseY < y + this.getHeight();
        if (hovered) {
            ScreenDrawing.rect(spriteHovered, x, y, spriteWidth, spriteHeight, -1);
        } else {
            ScreenDrawing.rect(sprite, x, y, spriteWidth, spriteHeight, -1);
        }
    }

    @Override
    public void setSize(int x, int y) {
        super.width = x;
        super.height = y;
    }


}
