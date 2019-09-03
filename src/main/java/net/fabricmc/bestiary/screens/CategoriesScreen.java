package net.fabricmc.bestiary.screens;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;

public class CategoriesScreen extends BaseBestiaryScreen {

    ButtonWidget friendlyButton;
    ButtonWidget neutralButton;
    ButtonWidget hostileButton;
    ButtonWidget bossButton;

    @Override
    protected void init() {
        super.init();
        initButtons();
    }

    private void initButtons() {
        friendlyButton = new TexturedButtonWidget(height / 2, width / 2, 200, 200, 200, 200, 200, new Identifier("textures/entity/sheep/sheep.png"), (widget) -> {

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
        friendlyButton.render(int_1, int_2, float_1);
        super.render(int_1, int_2, float_1);
    }
}
