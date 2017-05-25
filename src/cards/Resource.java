package cards;

import effects.Effect;

import java.awt.*;


public class Resource extends Card{
    private Effect resourceEffect;


    public Resource(String type, String name, String price, int minimumPlayers, Image picture, Effect resourceEffect) {
        super(type, name, price, minimumPlayers, picture);
        this.resourceEffect = resourceEffect;

    }

    public Effect getResourceEffect() {
        return resourceEffect;
    }
}
