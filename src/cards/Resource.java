package cards;

import effects.Effect;

import java.awt.*;


public class Resource extends Card{
    private Effect resourceEffect;
    private String canBuild;
    private String canBuild2;

    public Resource(String type, String name, String price, int minimumPlayers, Image picture, Effect resourceEffect, String canBuild, String canBuild2) {
        super(type, name, price, minimumPlayers, picture);
        this.resourceEffect = resourceEffect;
        this.canBuild=canBuild;
        this.canBuild2=canBuild2;
    }

    public Effect getResourceEffect() {
        return resourceEffect;
    }
}
