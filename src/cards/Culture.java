package cards;

import effects.Effect;

import java.awt.*;

/**
 * Created by mkrec_000 on 22/02/2017.
 */
public class Culture extends Card{
    private Effect cultureEffect;
    private String canBuild;
    private String canBuild2;

    public Culture(String type, String name, String price, int minimumPlayers, Image picture, Effect cultureEffect, String canBuild, String canBuild2) {
        super(type, name, price, minimumPlayers, picture);
        this.cultureEffect = cultureEffect;
        this.canBuild=canBuild;
        this.canBuild2=canBuild2;
    }

    public Effect getCultureEffect() {
        return cultureEffect;
    }
}
