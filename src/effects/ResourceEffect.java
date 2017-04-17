package effects;

public class ResourceEffect extends Effect{
    String resourceType;

    public ResourceEffect(String effectType, String resourceType) {
        super(effectType);
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }
}
