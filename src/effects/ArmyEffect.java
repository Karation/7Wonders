package effects;

public class ArmyEffect extends Effect{
    private int numOfShields;

    ArmyEffect(String effectType, int numOfShields) {
        super(effectType);
        this.numOfShields = numOfShields;
    }

    public int getNumOfShields() {
        return numOfShields;
    }
}
