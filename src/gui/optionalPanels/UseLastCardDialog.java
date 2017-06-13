package gui.optionalPanels;

import cards.Card;
import player.Player;
import player.action.Build;
import player.action.Sell;
import player.action.UseForWonder;
import wonders.WonderStage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UseLastCardDialog extends JDialog {
    private Player player;
    private Player opponent;
    private boolean chosen=false;
    public UseLastCardDialog(Player player, Player opponent){
        this.player=player;
        this.opponent=opponent;
    }
    public boolean createDialog() {
        ArrayList<Card> cardsInHand = opponent.getCopiedCardsInHand();
        player.setCardsInHand(cardsInHand);
        Card card = opponent.getCopiedCardsInHand().get(0);

        this.setTitle("Choose action for this card");
        this.setLayout(new GridBagLayout());
        this.setSize(500,500);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        c.gridx=0;
        ImageIcon cardIcon = new ImageIcon(card.getImage());
        JPanel cardPanel = new JPanel(new FlowLayout());
        JButton iconButton = new JButton(cardIcon);
        cardPanel.add(iconButton);
        this.add(cardPanel, c);

        c.gridx=0;
        c.gridy=1;
        JButton buildButton = new JButton("Build");
        this.add(buildButton, c);

        c.gridy=2;
        c.gridx=0;
        JButton sellButton = new JButton("Sell");
        this.add(sellButton);

        c.gridx=0;
        c.gridy=3;
        JButton useForWonderButton = new JButton("Use for Wonder");
        this.add(useForWonderButton);

        ActionListener choiceListener = e -> {
            if (e.getSource() == buildButton) {
                Set<Boolean> checkedIfCanBuildBuilding = new HashSet<>(player.checkIfCanBuild(card, null));
                if (checkedIfCanBuildBuilding.contains(false)){
                    JOptionPane.showMessageDialog(UseLastCardDialog.this,
                            "Cant Build, no required resources",
                            "Building warning",
                            JOptionPane.WARNING_MESSAGE);
                }else {
                    player.setAction(new Build(card, false));
                    player.startAction();
                    UseLastCardDialog.this.setVisible(false);
                    chosen=true;
                }
            }else if(e.getSource()==sellButton){
                player.setAction(new Sell(0));
                player.startAction();
                UseLastCardDialog.this.setVisible(false);
                chosen = true;
            }else if (e.getSource()==useForWonderButton){
                WonderStage wonderStageToBuild=player.getWonder().checkWhichWonderStageBuild();
                Set<Boolean> checkedIfCanBuildWonder = new HashSet<>(player.checkIfCanBuild(card, wonderStageToBuild));
                if(checkedIfCanBuildWonder.contains(false)){
                    JOptionPane.showMessageDialog(UseLastCardDialog.this,
                            "Cant Build, no required resources",
                            "Building warning",
                            JOptionPane.WARNING_MESSAGE);
                }else {
                    player.setAction(new UseForWonder(card));
                    player.startAction();
                    UseLastCardDialog.this.setVisible(false);
                    chosen=true;
                }
            }
        };
        buildButton.addActionListener(choiceListener);
        sellButton.addActionListener(choiceListener);
        useForWonderButton.addActionListener(choiceListener);
        this.setVisible(true);
        return chosen;
    }
}
