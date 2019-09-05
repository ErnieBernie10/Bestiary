package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
import net.fabricmc.bestiary.gui.widget.WSpriteButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class CategoriesBestiaryGUI extends BaseBestiaryGUI {

    private WButton friendlyButton;
    private WButton neutralButton;
    private WButton hostileButton;
    private WButton bossButton;
    private ClientCottonScreen friendlyCategoryScreen;
    private ClientCottonScreen neutralCategoryScreen;
    private ClientCottonScreen hostileCategoryScreen;
    private ClientCottonScreen bossCategoryScreen;


    public CategoriesBestiaryGUI() {
        super();
        friendlyCategoryScreen = new BestiaryScreen(new NavigatableGUI(Identifiers.FRIENDLY_PAGE_BACKGROUND, this));
        neutralCategoryScreen = new BestiaryScreen(new NavigatableGUI(Identifiers.NEUTRAL_PAGE_BACKGROUND, this));
        hostileCategoryScreen = new BestiaryScreen(new NavigatableGUI(Identifiers.HOSTILE_PAGE_BACKGROUND, this));
        bossCategoryScreen = new BestiaryScreen(new NavigatableGUI(Identifiers.BOSS_PAGE_BACKGROUND, this));

        friendlyButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/passive_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/passive_selected_bookmark.png"),
                32,
                171);
        friendlyButton.setOnClick(() -> openScreen(friendlyCategoryScreen));
        getRoot().add(friendlyButton, 10, 0, 2, 10);

        neutralButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/neutral_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/neutral_selected_bookmark.png"),
                32,
                171);
        neutralButton.setOnClick(() -> openScreen(neutralCategoryScreen));
        getRoot().add(neutralButton, 12, 0, 2, 10);

        hostileButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/hostile_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/hostile_selected_bookmark.png"),
                32,
                171);
        hostileButton.setOnClick(() -> openScreen(hostileCategoryScreen));
        getRoot().add(hostileButton, 14, 0, 2, 10);

        bossButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/boss_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/boss_selected_bookmark.png"),
                32,
                171);
        bossButton.setOnClick(() -> openScreen(bossCategoryScreen));
        getRoot().add(bossButton, 16, 0, 2, 10);
    }


}
