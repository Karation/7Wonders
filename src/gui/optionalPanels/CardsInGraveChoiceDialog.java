package gui.optionalPanels;

import cards.Card;
import player.Player;
import player.action.Build;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardsInGraveChoiceDialog extends JDialog{
    private ArrayList<Card> cardsInGrave = new ArrayList<>();
    private Player player;
    public CardsInGraveChoiceDialog(ArrayList<Card> cardsInGrave, Player player){
        this.cardsInGrave = cardsInGrave;
        this.player = player;

    }

    public void createDialog() {
        Card tempCard = cardsInGrave.get(0);
        int height = tempCard.getImage().getHeight(this);
        int  width=tempCard.getImage().getWidth(this);
        this.setTitle("Choose card from grave");
        this.setLayout(new GridLayout(1, cardsInGrave.size()));
        this.setSize(width*cardsInGrave.size(), height);

        for (int i = 0; i < cardsInGrave.size(); i++) {
            final int x = i;
            ImageIcon cardsImage = new ImageIcon(cardsInGrave.get(i).getImage());
            JButton button = new JButton(cardsImage);
            button.addActionListener(e -> {
                player.setAction(new Build(cardsInGrave.get(x), false));
                player.startAction();
                CardsInGraveChoiceDialog.this.setVisible(false);
            });
            this.add(button);
        }
        this.setVisible(true);
    }

    public ArrayList<Card> getCardsInGrave() {
        return cardsInGrave;
    }

    public void setCardsInGrave(ArrayList<Card> cardsInGrave) {
        this.cardsInGrave = cardsInGrave;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
