package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.bestiary.gui.widget.WSpriteButton;
import net.minecraft.util.Identifier;

public class CategoriesBestiaryGUI extends BaseBestiaryGUI {

    private WButton friendlyButton;
    private WButton neutralButton;
    private WButton hostileButton;
    private WButton bossButton;

    public CategoriesBestiaryGUI() {
        super();
        friendlyButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/passive_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/passive_selected_bookmark.png"),
                32,
                171);
        getRoot().add(friendlyButton, 10, 0, 2, 10);

        neutralButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/neutral_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/neutral_selected_bookmark.png"),
                32,
                171);
        getRoot().add(neutralButton, 12, 0, 2, 10);

        hostileButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/hostile_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/hostile_selected_bookmark.png"),
                32,
                171);
        getRoot().add(hostileButton, 14, 0, 2, 10);

        bossButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/boss_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/boss_selected_bookmark.png"),
                32,
                171);
        getRoot().add(bossButton, 16, 0, 2, 10);
    }
}
