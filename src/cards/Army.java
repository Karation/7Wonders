package cards;

import effects.Effect;

import java.awt.*;

/**
 * Created by mkrec_000 on 22/02/2017.
 */
public class Army extends Card{
    private Effect armyEffect;
    private String canBuild;

    public Army(String type, String name, String price, int minimumPlayers, Image picture, Effect armyEffect, String canBuild) {
        super(type, name, price, minimumPlayers, picture);
        this.armyEffect = armyEffect;
        this.canBuild=canBuild;
    }

    public Effect getArmyEffect() {
        return armyEffect;
    }

    public String getCanBuild() {
        return canBuild;
    }

    public void setCanBuild(String canBuild) {
        this.canBuild = canBuild;
    }


}
