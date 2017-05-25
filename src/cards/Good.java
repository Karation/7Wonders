package cards;

import effects.Effect;

import java.awt.*;


public class Good extends Card{
    private Effect goodEffect;


    public Good(String type, String name, String price, int minimumPlayers, Image picture, Effect goodEffect) {
        super(type, name, price, minimumPlayers, picture);
        this.goodEffect = goodEffect;

    }

    public Effect getGoodEffect() {
        return goodEffect;
    }
}
