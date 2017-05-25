package effects;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public class ScienceEffect extends Effect{
    private String scienceSymbol;

    ScienceEffect(String effectType, String scienceSymbol) {
        super(effectType);
        this.scienceSymbol = scienceSymbol;
    }

    public String getScienceSymbol() {
        return scienceSymbol;
    }
}
