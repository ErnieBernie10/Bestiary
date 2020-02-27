package net.erniebernie.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.erniebernie.bestiary.BestiaryMod;
import net.erniebernie.bestiary.gui.models.BeastDetail;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DetailBestiaryGui extends LightweightGuiDescription {

    private Identifier background;
    private String id;
    private BeastDetail detail;

    public DetailBestiaryGui(String id, Identifier background) {
        this.id = id;
        this.background = background;
        this.detail = BestiaryMod.BEAST_DETAILS.get(id);

        WGridPanel root = new WGridPanel();
        root.setSize(320, 200);
        setRootPanel(root);

        addHealth(root);

        root.validate(this);
    }

    private void addHealth(WGridPanel root) {
        Identifier heart = new Identifier("minecraft", "textures/gui/icons.png");
        for (int i = 0; i < detail.getHealth() / 2; i++) {
            ScreenDrawing.texturedRect(200 + i * 16, 50, 16, 16, heart, 64, 0, 80,16 , -1);
        }
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter((i, i1, wWidget) -> ScreenDrawing.texturedRect(i, i1, wWidget.getWidth(), wWidget.getHeight(), background, -1));
    }
}
