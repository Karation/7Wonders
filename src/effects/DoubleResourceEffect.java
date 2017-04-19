package effects;

/**
 * Created by mkrec_000 on 19/04/2017.
 */
public class DoubleResourceEffect extends Effect{
    String effectName;
    String resourceType;
    int numberOfResources;
    public DoubleResourceEffect(String effectType, String resourceType, int numberOfResources, String effectName) {
        super(effectType);
        this.resourceType = resourceType;
        this.numberOfResources=numberOfResources;
        this.effectName = effectName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }
}
