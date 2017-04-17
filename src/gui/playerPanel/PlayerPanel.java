package gui.playerPanel;

import cards.Card;
import player.Player;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class PlayerPanel extends JPanel {
    CardsPanel cardsPanel;
    WonderPanel wonderPanel;
    public PlayerPanel(ArrayList<Card> cards, Player player, Wonders wonder) throws IOException{
        setLayout(new GridBagLayout());
        cardsPanel = new CardsPanel(cards, player);
        if (wonderPanel==null) {
            wonderPanel = new WonderPanel(wonder);
        }
        else {
            wonderPanel=this.getWonderPanel();
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.CENTER;
        this.add(wonderPanel, c);
        c.gridy++;
        c.anchor=GridBagConstraints.CENTER;
        this.add(cardsPanel, c);
        this.setOpaque(false);
    }

    public CardsPanel getCardsPanel() {
        return cardsPanel;
    }
    public WonderPanel getWonderPanel() {
        return wonderPanel;
    }

    public void setCardsPanel(CardsPanel cardsPanel) {
        this.cardsPanel = cardsPanel;
    }
}
