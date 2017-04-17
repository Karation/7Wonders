package effects;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public class ArmyEffect extends Effect{
    int numOfShields;

    public ArmyEffect(String effectType, int numOfShields) {
        super(effectType);
        this.numOfShields = numOfShields;
    }

    public int getNumOfShields() {
        return numOfShields;
    }
}
