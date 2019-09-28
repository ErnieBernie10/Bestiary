package net.fabricmc.bestiary.gui.widget;

import io.github.cottonmc.cotton.gui.client.LibGuiClient;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class WCustomLabel extends WWidget {
    protected Text text;
    protected int color;
    protected int darkmodeColor;
    public static final int DEFAULT_TEXT_COLOR = 4210752;
    public static final int DEFAULT_DARKMODE_TEXT_COLOR = 12369084;

    public WCustomLabel(String text, int color) {
        this((Text)(new LiteralText(text)), color);
    }

    public WCustomLabel(Text text, int color) {
        this.text = text;
        this.color = color;
        this.darkmodeColor = color == 4210752 ? 12369084 : color;
    }

    public WCustomLabel(String text) {
        this(text, 4210752);
    }

    @Override
    public void paintForeground(int x, int y, int mouseX, int mouseY) {
        String translated = this.text.asFormattedString();
        ScreenDrawing.drawString(translated, x, y, LibGuiClient.config.darkMode ? this.darkmodeColor : this.color);
    }

    public boolean canResize() {
        return true;
    }

    public void setSize(int x, int y) {
        super.setSize(x, 20);
    }

    public WCustomLabel setDarkmodeColor(int color) {
        this.darkmodeColor = color;
        return this;
    }

    public WCustomLabel disableDarkmode() {
        this.darkmodeColor = this.color;
        return this;
    }

    public WCustomLabel setColor(int color, int darkmodeColor) {
        this.color = color;
        this.darkmodeColor = darkmodeColor;
        return this;
    }

    public WCustomLabel setText(Text text) {
        this.text = text;
        return this;
    }
}
