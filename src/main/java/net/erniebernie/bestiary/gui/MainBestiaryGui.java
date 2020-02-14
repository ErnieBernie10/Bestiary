package net.erniebernie.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.erniebernie.bestiary.components.BestiaryComponent;
import net.erniebernie.bestiary.gui.screens.BestiaryScreen;
import net.erniebernie.bestiary.gui.widgets.WSpriteButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class MainBestiaryGui extends LightweightGuiDescription {

    private BestiaryComponent counter;

    public MainBestiaryGui(BestiaryComponent counter) {
        this.counter = counter;

        WGridPanel root = new WGridPanel();
        root.setSize(320, 200);
        setRootPanel(root);

        MinecraftClient client = MinecraftClient.getInstance();

        // TODO : Centralize texture Identifiers

        WSpriteButton btnPassives = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/passive_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/passive_selected_bookmark.png"));
        btnPassives.setOnClick(() -> {
            client.openScreen(new BestiaryScreen(new CategoryBestiaryGui(counter.getPassiveProgress(), new Identifier("fabric-bestiary", "textures/gui/passive_toc.png"))));
        });
        btnPassives.setSize(32, 171);
        root.add(btnPassives, 10, 0);

        WSpriteButton btnNeutrals = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/neutral_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/neutral_selected_bookmark.png"));
        btnNeutrals.setOnClick(() -> {
            client.openScreen(new BestiaryScreen(new CategoryBestiaryGui(counter.getNeutralProgress(), new Identifier("fabric-bestiary", "textures/gui/neutral_toc.png"))));
        });
        btnNeutrals.setSize(32, 171);
        root.add(btnNeutrals, 12, 0);

        WSpriteButton btnHostiles = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/hostile_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/hostile_selected_bookmark.png"));
        btnHostiles.setOnClick(() -> {
            client.openScreen(new BestiaryScreen(new CategoryBestiaryGui(counter.getHostileProgress(), new Identifier("fabric-bestiary", "textures/gui/hostile_toc.png"))));
        });
        btnHostiles.setSize(32, 171);
        root.add(btnHostiles, 14, 0);

        WSpriteButton btnBosses = new WSpriteButton(new Identifier("fabric-bestiary", "textures/gui/buttons/boss_bookmark.png"), new Identifier("fabric-bestiary", "textures/gui/buttons/boss_selected_bookmark.png"));
        btnBosses.setOnClick(() -> {
            client.openScreen(new BestiaryScreen(new CategoryBestiaryGui(counter.getBossProgress(), new Identifier("fabric-bestiary", "textures/gui/boss_toc.png"))));
        });
        btnBosses.setSize(32, 171);
        root.add(btnBosses, 16, 0);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter((i, i1, wWidget) -> ScreenDrawing.texturedRect(i, i1, wWidget.getWidth(), wWidget.getHeight(), new Identifier("fabric-bestiary", "textures/gui/blank_page.png"), -1));
    }
}
