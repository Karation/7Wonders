package effects;

public class ResourceEffect extends Effect{
    private String resourceType;

    ResourceEffect(String effectType, String resourceType) {
        super(effectType);
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }
}
