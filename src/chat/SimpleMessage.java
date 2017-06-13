package chat;

/**
 * Created by mkrec_000 on 06/06/2017.
 */
public class SimpleMessage implements Message{
    private String value;

    public SimpleMessage() {
    }

    public SimpleMessage(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
