package net.fabricmc.bestiary.gui.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;

public class CategoriesScreen extends CategoriesBestiaryScreen {

    ButtonWidget friendlyButton;
    ButtonWidget neutralButton;
    ButtonWidget hostileButton;
    ButtonWidget bossButton;

    public CategoriesScreen(GuiDescription description) {
        super(description);
    }

    @Override
    protected void init() {
        super.init();
        initButtons();
    }

    private void initButtons() {
        friendlyButton = new TexturedButtonWidget(500, 50, 32, 171, 0, 0, 0, new Identifier("bestiary", "textures/gui/buttons/passive_bookmark.png"), 32, 171, (widget) -> {

        });
        neutralButton = new TexturedButtonWidget(height / 2, width / 2, 200, 200, 200, 200, 200, new Identifier(""), (widget) -> {

        });
        hostileButton = new TexturedButtonWidget(height / 2, width / 2, 200, 200, 200, 200, 200, new Identifier(""), (widget) -> {

        });
        bossButton = new TexturedButtonWidget(height / 2, width / 2, 200, 200, 200, 200, 200, new Identifier(""), (widget) -> {

        });
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        super.render(int_1, int_2, float_1);
        friendlyButton.render(int_1, int_2, float_1);

    }
}
