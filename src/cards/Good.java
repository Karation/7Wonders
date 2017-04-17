package cards;

import effects.Effect;

import java.awt.*;

/**
 * Created by mkrec_000 on 22/02/2017.
 */
public class Good extends Card{
    private Effect goodEffect;
    private String canBuild;
    private String canBuild2;

    public Good(String type, String name, String price, int minimumPlayers, Image picture, Effect goodEffect, String canBuild, String canBuild2) {
        super(type, name, price, minimumPlayers, picture);
        this.goodEffect = goodEffect;
        this.canBuild=canBuild;
        this.canBuild2=canBuild2;
    }

    public Effect getGoodEffect() {
        return goodEffect;
    }
}
