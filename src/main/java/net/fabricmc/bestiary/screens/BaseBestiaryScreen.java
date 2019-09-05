package net.fabricmc.bestiary.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class BaseBestiaryScreen extends ClientCottonScreen {

    public static final Identifier BOOK_BACKGROUND = new Identifier("bestiary", "textures/gui/blank_page.png");

    public BaseBestiaryScreen(GuiDescription description) {
        super(description);
    }


//    @Override
//    protected void init() {
//        super.init();
//    }

//    @Override
//    public void render(int int_1, int int_2, float float_1) {
//        this.renderBackground();
//        this.minecraft.getTextureManager().bindTexture(BOOK_BACKGROUND);
//        int int_3 = (this.width - 320) / 2;
//        this.blit(100, 50, 0, 0, 256, 256);
//        super.render(int_1, int_2, float_1);
//    }
}
