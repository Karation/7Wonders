package cards;

import java.awt.*;


public abstract class Card {
    private String name;
    private String price;
    private Integer minimumPlayers;
    private Image picture;
    private String type;

    public Card(String type, String name, String price, int minimumPlayers, Image picture) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.minimumPlayers = minimumPlayers;
        this.picture=picture;
    }

    public String getName() {
        return name;
    }
    public Image getImage(){return picture;}
    public String getType(){return type;}
    public String getPrice() {return price;}
}
