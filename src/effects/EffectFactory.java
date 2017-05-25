package effects;

public class EffectFactory {
    public static Effect create(String type, String effect) {
        switch (type) {
            case "Army":
                return new ArmyEffect(type, Integer.parseInt(effect));
            case "Culture":
                return new CultureEffect(type, Integer.parseInt(effect));
            case "Good":
                return new GoodEffect(type, effect);
            case "Resource":
                String[] effects = effect.split("(?<=.)(?=\\p{Lu})");
                if (effects.length == 1) {
                    return new ResourceEffect(type, effect);
                }
                if (effects[0].equals(effects[1])) {
                    return new DoubleResourceEffect(type, effects[0], 2, effect);
                } else {
                    return new MixedResourceEffect(type, effect, effects[0], effects[1]);
                }
            case "Science":
                return new ScienceEffect(type, effect);
            case "Trade":
                return new TradeEffect(type, effect);
            case "Money":
                return new MoneyEffect(type, Integer.parseInt(effect));
            case "WonderEffect":
                return new WonderEffect(type, effect);
            case "Guild":
                return new GuildEffect(type, effect);
            default:
                return null;
        }
    }
}
