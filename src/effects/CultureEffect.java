package effects;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public class CultureEffect extends Effect{
    int numOfPoints;

    public CultureEffect(String effectType, int numOfPoints) {
        super(effectType);
        this.numOfPoints = numOfPoints;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }
}
