package cards;

import effects.Effect;

import java.awt.*;

/**
 * Created by mkrec_000 on 22/02/2017.
 */
public class Science extends Card{
    private Effect scienceEffect;
    private String canBuild;
    private String canBuild2;

    public Science(String type, String name, String price, int minimumPlayers, Image picture, Effect scienceEffect, String canBuild, String canBuild2) {
        super(type, name, price, minimumPlayers, picture);
        this.scienceEffect = scienceEffect;
        this.canBuild=canBuild;
        this.canBuild2=canBuild2;
    }

    public Effect getScienceEffect() {
        return scienceEffect;
    }

    public String getCanBuild() {
        return canBuild;
    }

    public void setCanBuild(String canBuild) {
        this.canBuild = canBuild;
    }

    public String getCanBuild2() {
        return canBuild2;
    }

    public void setCanBuild2(String canBuild2) {
        this.canBuild2 = canBuild2;
    }
}
