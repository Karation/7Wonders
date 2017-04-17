package effects;

/**
 * Created by mkrec_000 on 05/04/2017.
 */
public class MoneyEffect extends Effect{
    int amount;

    public MoneyEffect(String effectType, int amount) {
        super(effectType);
        this.amount=amount;
    }

    public int getAmount() {
        return amount;
    }
}
