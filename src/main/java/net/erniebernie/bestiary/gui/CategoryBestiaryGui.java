package net.erniebernie.bestiary.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import net.erniebernie.bestiary.gui.widgets.BestiaryListItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class CategoryBestiaryGui extends LightweightGuiDescription {

    private Map<String, Integer> category;
    private Identifier background;

    public Identifier getBackground() {
        return background;
    }

    public CategoryBestiaryGui(Map<String, Integer> category, Identifier background) {
        this.category = category;
        this.background = background;

        WGridPanel root = new WGridPanel();
        root.setSize(320, 200);
        setRootPanel(root);

        BiConsumer<DataModel, BestiaryListItem> config = (data, bestiaryListItem) -> {
            Text name = Registry.ENTITY_TYPE.get(new Identifier(data.id)).getName();
            bestiaryListItem.setName(name.asString());
            bestiaryListItem.setProgress(data.progress);
            bestiaryListItem.setId(data.id);
            bestiaryListItem.setHost(this);
        };

        WListPanel<DataModel, BestiaryListItem> progressList = new WListPanel<>(mapToList(category), BestiaryListItem::new, config);

        root.add(progressList, 0, 0, 9, 10);

        root.validate(this);
    }

    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter((i, i1, wWidget) -> ScreenDrawing.texturedRect(i, i1, wWidget.getWidth(), wWidget.getHeight(), background, -1));
    }

    // TODO : Find a way to use the Map itself instead of having to convert it to a list
    public List<DataModel> mapToList(Map<String, Integer> map) {
        List<DataModel> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(new DataModel(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private static class DataModel {
        String id;
        int progress;

        public DataModel(String id, int progress) {
            this.id = id;
            this.progress = progress;
        }
    }
}
