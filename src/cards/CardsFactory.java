package cards;

import effects.EffectFactory;

import java.awt.*;

class CardsFactory {
    static Card create(String type, String name, String price, int minimumPlayers, Image picture, String effect, String canBuild, String canBuild2) {
        switch (type) {
            case "Army":
                return new Army(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild);
            case "Culture":
                return new Culture(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
            case "Trade":
                return new Trade(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
            case "Good":
                return new Good(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect));
            case "Resource":
                return new Resource(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect));
            case "Science":
                return new Science(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect), canBuild, canBuild2);
            case "Guild":
                return new Guild(type, name, price, minimumPlayers, picture, EffectFactory.create(type, effect));
            default:
                return null;
        }
    }
}
