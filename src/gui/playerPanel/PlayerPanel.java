package gui.playerPanel;

import cards.Card;
import gui.playerPanel.cardsPanel.CardsPanel;
import gui.playerPanel.wonderPanel.WonderPanel;
import player.Player;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class PlayerPanel extends JPanel {
    private CardsPanel cardsPanel;
    private WonderPanel wonderPanel;
    private CanBuildPanel canBuildPanel;
    private ChatPanel chatPanel;
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

        canBuildPanel = new CanBuildPanel();
        c.gridx=0;
        c.gridy=0;
        c.gridheight=2;
        c.anchor=GridBagConstraints.LINE_START;
        this.add(canBuildPanel, c);

        c.gridx=1;
        c.gridy=0;
        c.gridheight=1;
        c.anchor=GridBagConstraints.CENTER;
        this.add(wonderPanel, c);

        c.gridy=1;
        c.gridheight=1;
        c.anchor=GridBagConstraints.CENTER;
        this.add(cardsPanel, c);
        this.setOpaque(false);

        chatPanel = new ChatPanel(player, player.getBoard().getChatSession());
        c.gridx=2;
        c.gridy=0;
        c.gridheight=2;
        c.anchor=GridBagConstraints.LINE_END;
        this.add(chatPanel, c);
//        c.gridx=1;
//        c.gridy=1;
//        //c.fill=GridBagConstraints.
//        c.ipady=1;
//        c.weighty=2;
//        c.anchor=GridBagConstraints.CENTER;
//        ChatPanel chatPanel = new ChatPanel();
//        this.add(chatPanel, c);

//        this.setLayout(new FlowLayout());
//        cardsPanel = new CardsPanel(cards, player);
//        if (wonderPanel==null) {
//            wonderPanel = new WonderPanel(wonder);
//        }
//        else {
//            wonderPanel=this.getWonderPanel();
//        }
//        this.add(wonderPanel);

    }

    public CardsPanel getCardsPanel() {
        return cardsPanel;
    }
    public WonderPanel getWonderPanel() {
        return wonderPanel;
    }

    public CanBuildPanel getCanBuildPanel() {
        return canBuildPanel;
    }

    public void setCardsPanel(CardsPanel cardsPanel) {
        this.cardsPanel = cardsPanel;
    }
}
