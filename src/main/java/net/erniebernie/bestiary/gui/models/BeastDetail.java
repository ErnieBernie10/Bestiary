package net.erniebernie.bestiary.gui.models;

public class BeastDetail implements Comparable<BeastDetail> {
    private String id;
    private String description;
    private int damageModifier;

    public BeastDetail() {
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(BeastDetail o) {
        return this.id.compareTo(o.getId());
    }
}
