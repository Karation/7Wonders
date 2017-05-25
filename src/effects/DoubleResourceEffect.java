package effects;

public class DoubleResourceEffect extends Effect{
    private String effectName;
    private String resourceType;
    private int numberOfResources;
    DoubleResourceEffect(String effectType, String resourceType, int numberOfResources, String effectName) {
        super(effectType);
        this.resourceType = resourceType;
        this.numberOfResources=numberOfResources;
        this.effectName = effectName;
    }

    public String getResourceType() {
        return resourceType;
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

}
