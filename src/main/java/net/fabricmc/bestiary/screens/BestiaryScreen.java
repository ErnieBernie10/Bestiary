package net.fabricmc.bestiary.screens;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

public class BestiaryScreen extends Screen {

    private ButtonWidget testButton;

    public BestiaryScreen(Text text_1) {
        super(text_1);

    }

    private void generateButtons() {
        testButton = this.addButton(new ButtonWidget(this.width / 2 - 100, 196, 98, 20, "TEST", (buttonWidget_1) -> {

        }));
    }

    @Override
    protected void init() {
        generateButtons();
        super.init();
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        this.font.draw("TEST", (float)(50 + 36 + (114 - 50) / 2), 34.0F, 0);
        testButton.render(int_1, int_2, float_1);
        super.render(int_1, int_2, float_1);
    }
}
