package effects;

public class CultureEffect extends Effect{
    private int numOfPoints;

    CultureEffect(String effectType, int numOfPoints) {
        super(effectType);
        this.numOfPoints = numOfPoints;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }
}
