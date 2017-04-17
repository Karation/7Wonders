package effects;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public class GoodEffect extends Effect{
    String goodType;

    public GoodEffect(String effectType, String goodType) {
        super(effectType);
        this.goodType = goodType;
    }

    public String getGoodType() {
        return goodType;
    }
}
