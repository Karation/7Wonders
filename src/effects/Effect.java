package effects;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public abstract class Effect {
    String effectType;

    public Effect(String effectType) {
        this.effectType = effectType;
    }

    public String getEffectType() {
        return effectType;
    }
}
