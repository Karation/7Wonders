package effects;

/**
 * Created by mkrec_000 on 30/03/2017.
 */
public class MixedResourceEffect extends Effect {

    private String effectName;
    private String resourceType1;
    private String resourceType2;

    public MixedResourceEffect(String type, String effectName, String resourceType1, String resourceType2) {
        super(type);
        this.effectName=effectName;
        this.resourceType1 = resourceType1;
        this.resourceType2 = resourceType2;
    }
    public String getResourceType1() {
        return resourceType1;
    }

    public String getResourceType2() {
        return resourceType2;
    }

    public String getEffectName() {
        return effectName;
    }
}
