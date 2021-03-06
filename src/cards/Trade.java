package cards;

import effects.Effect;

import java.awt.*;

public class Trade extends Card{
    private Effect tradeEffect;
    private String canBuild;
    private String canBuild2;

        public Trade(String type, String name, String price, int minimumPlayers, Image picture, Effect tradeEffect, String canBuild, String canBuild2) {
            super(type, name, price, minimumPlayers, picture);
            this.tradeEffect = tradeEffect;
            this.canBuild=canBuild;
            this.canBuild2=canBuild2;
        }

    public Effect getTradeEffect() {
        return tradeEffect;
    }

    public String getCanBuild() {
        return canBuild;
    }

}
