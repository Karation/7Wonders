package wonders;

import effects.Effect;

import java.util.ArrayList;

public class WonderStage {
    private int stage;
    private String price;
    private ArrayList<Effect> effects;
    private boolean isBuilt=false;

    WonderStage(int stage, String price, ArrayList<Effect> effects) {
        this.stage = stage;
        this.price = price;
        this.effects = effects;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }

    public int getStage() {
        return stage;
    }

    boolean IsBuilt() {
        return isBuilt;
    }

    public void setBuilt() {
        isBuilt = true;
    }
}
