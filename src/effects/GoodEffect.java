package effects;

public class GoodEffect extends Effect{
    private String goodType;

    GoodEffect(String effectType, String goodType) {
        super(effectType);
        this.goodType = goodType;
    }

    public String getGoodType() {
        return goodType;
    }
}
