package effects;

public abstract class Effect {
    String effectType;

    public Effect(String effectType) {
        this.effectType = effectType;
    }

    public String getEffectType() {
        return effectType;
    }
}
