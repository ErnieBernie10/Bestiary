package net.erniebernie.bestiary.gui.widgets;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.erniebernie.bestiary.gui.CategoryBestiaryGui;
import net.erniebernie.bestiary.gui.DetailBestiaryGui;
import net.erniebernie.bestiary.gui.screens.BestiaryScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class BestiaryListItem extends WPlainPanel {

    private String id;
    private WLabel name;
    private WLabel progress;
    private WButton details;
    private CategoryBestiaryGui host;

    public BestiaryListItem() {
        this.name = new WLabel("");
        this.add(name, 0, 0);
        this.progress = new WLabel("");
        this.add(progress, 50, 0);
        this.details = new WButton(new LiteralText("Details"));
        this.details.setOnClick(() -> MinecraftClient.getInstance().openScreen(new BestiaryScreen(new DetailBestiaryGui(id, host.getBackground()))));
        this.add(details, 100, 0);
        this.id = "";
    }

    public void setName(String name) {
        this.name.setText(new LiteralText(name));
    }

    public void setProgress(int progress) {
        this.progress.setText(new LiteralText(Integer.toString(progress)));
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHost(CategoryBestiaryGui host) {
        this.host = host;
    }
}
