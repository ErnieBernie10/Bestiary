package net.fabricmc.bestiary.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.bestiary.Bestiary;
import net.fabricmc.bestiary.KillCounterModel;
import net.fabricmc.bestiary.components.MobKillsComponent;
import net.fabricmc.bestiary.gui.screens.BestiaryScreen;
import net.fabricmc.bestiary.gui.widget.WSpriteButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class CategoriesBestiaryGUI extends BaseBestiaryGUI {

    private WButton friendlyButton;
    private WButton neutralButton;
    private WButton hostileButton;
    private WButton bossButton;
    private NavigatableGUI friendlyCategoryGUI;
    private NavigatableGUI neutralCategoryGUI;
    private NavigatableGUI hostileCategoryGUI;
    private NavigatableGUI bossCategoryGUI;
    private PlayerEntity owner;

    public CategoriesBestiaryGUI(PlayerEntity owner) {
        super();
        friendlyCategoryGUI = new NavigatableGUI(Identifiers.FRIENDLY_PAGE_BACKGROUND, this);
        neutralCategoryGUI = new NavigatableGUI(Identifiers.NEUTRAL_PAGE_BACKGROUND, this);
        hostileCategoryGUI = new NavigatableGUI(Identifiers.HOSTILE_PAGE_BACKGROUND, this);
        bossCategoryGUI = new NavigatableGUI(Identifiers.BOSS_PAGE_BACKGROUND, this);
        this.owner = owner;

        friendlyButton = new WSpriteButton(
                new Identifier("bestiary", "textures/gui/buttons/passive_bookmark.png"),
                new Identifier("bestiary", "textures/gui/buttons/passive_selected_bookmark.png"),
                32,
                171);
        friendlyButton.setOnClick(() -> {
            MobKillsComponent c = Bestiary.KILLS_COMPONENT.get(owner);
            friendlyCategoryGUI.setKills(KillCounterModel.createModelList(c.getPassiveKills()));
            openScreen(new BestiaryScreen(friendlyCategoryGUI));
        });
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

    public PlayerEntity getOwner() {
        return owner;
    }
}
