package effects;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public class EffectFactory {
    public static Effect create(String type, String effect) {
        if (type.equals("Army")) {
            return new ArmyEffect(type, Integer.parseInt(effect));
        } else if (type.equals("Culture")) {
            return new CultureEffect(type, Integer.parseInt(effect));
        } else if (type.equals("Good")) {
            return new GoodEffect(type, effect);
        } else if (type.equals("Resource")) {
            String[] effects = effect.split("(?<=.)(?=\\p{Lu})");
            if (effects.length == 1) {
                return new ResourceEffect(type, effect);
            }
            if (effects[0].equals(effects[1])) {
                return new DoubleResourceEffect(type, effects[0], 2, effect);
            } else {
                return new MixedResourceEffect(type, effect, effects[0], effects[1]);
            }
        } else if (type.equals("Science")) {
            return new ScienceEffect(type, effect);
        } else if (type.equals("Trade")) {
            return new TradeEffect(type, effect);
        } else if (type.equals("Money")) {
            return new MoneyEffect(type, Integer.parseInt(effect));
        } else if (type.equals("WonderEffect")) {
            return new WonderEffect(type, effect);
        } else if (type.equals("Guild")) {
            return new GuildEffect(type, effect);
        } else return null;
    }
}
