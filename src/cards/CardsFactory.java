package cards;

import effects.EffectFactory;

import java.awt.*;

public class CardsFactory {
    public static Card create(String type, String name, String price, int minimumPlayers, Image picture, String effect, String canBuild, String canBuild2 ) {
        if(type.equals("Army")) return new Army(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
        else if(type.equals("Culture")) return new Culture(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
        else if(type.equals("Trade")) return new Trade(type, name, price, minimumPlayers, picture,EffectFactory.create(type, effect), canBuild, canBuild2);
        else if(type.equals("Good")) return new Good(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
        else if(type.equals("Resource")) return new Resource(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
        else if(type.equals("Science")) return new Science(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
        else if(type.equals("Guild")) return new Guild(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect));
        else return null;
    }
}
