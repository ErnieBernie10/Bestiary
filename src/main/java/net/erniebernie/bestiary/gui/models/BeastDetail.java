package net.erniebernie.bestiary.gui.models;

public class BeastDetail implements Comparable<BeastDetail> {
    private String id;
    private String description;
    private int damageModifier;
    private int lvl1Req;
    private int lvl2Req;
    private int health;

    public BeastDetail() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getLvl1Req() {
        return lvl1Req;
    }

    public void setLvl1Req(int lvl1Req) {
        this.lvl1Req = lvl1Req;
    }

    public int getLvl2Req() {
        return lvl2Req;
    }

    public void setLvl2Req(int lvl2Req) {
        this.lvl2Req = lvl2Req;
    }

    @Override
    public int compareTo(BeastDetail o) {
        return this.id.compareTo(o.getId());
    }
}
