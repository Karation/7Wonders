package cards;

import effects.Effect;

import java.awt.*;

/**
 * Created by mkrec_000 on 22/02/2017.
 */
public class Army extends Card{
    private Effect armyEffect;
    private String canBuild;
    private String canBuild2;

    public Army(String type, String name, String price, int minimumPlayers, Image picture, Effect armyEffect, String canBuild, String canBuild2) {
        super(type, name, price, minimumPlayers, picture);
        this.armyEffect = armyEffect;
        this.canBuild=canBuild;
        this.canBuild2=canBuild2;
    }

    public Effect getArmyEffect() {
        return armyEffect;
    }
}
