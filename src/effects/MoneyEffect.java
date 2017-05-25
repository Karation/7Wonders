package effects;

public class MoneyEffect extends Effect{
    private int amount;

    MoneyEffect(String effectType, int amount) {
        super(effectType);
        this.amount=amount;
    }

    public int getAmount() {
        return amount;
    }
}
