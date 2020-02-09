package net.erniebernie.bestiary.gui.widgets;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.util.Identifier;

public class WSpriteButton extends WButton {

    private Identifier texture;
    private Identifier hoveredTexture;

    public WSpriteButton(Identifier texture, Identifier hoveredTexture) {
        super();
        this.texture = texture;
        this.hoveredTexture = hoveredTexture;
    }

    @Override
    public void setSize(int x, int y) {
        super.width = x;
        super.height = y;
    }

    @Override
    public boolean canResize() {
        return false;
    }

    @Override
    public void paintBackground(int x, int y, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + this.getWidth() && mouseY < y + this.getHeight();
        if (hovered) {
            ScreenDrawing.texturedRect(x, y, this.getWidth(), this.getHeight(), hoveredTexture, -1);
        } else {
            ScreenDrawing.texturedRect(x, y, this.getWidth(), this.getHeight(), texture, -1);
        }
    }
}
