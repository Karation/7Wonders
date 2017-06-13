package gui.playerPanel.cardsPanel;

import cards.Card;
import effects.Effect;
import effects.WonderEffect;
import player.*;
import player.action.Build;
import player.action.Sell;
import player.action.UseForWonder;
import wonders.WonderStage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CardsPanel extends JPanel {
    private boolean didChoose = false;
    private JComboBox options;
    private AcceptButton acceptButton;


    public CardsPanel(ArrayList<Card> cards, Player player) throws IOException {
        this.setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        for (int i = 0; i < cards.size(); i++) {
            JPanel cardPanel = new JPanel();
            JButton button = new JButton();
            final int x = i;  //Action listener requiers final value.
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] choices=checkForBuildForFreeEffect(player);

                    options = new JComboBox(choices);
                    options.setPrototypeDisplayValue(button.getIcon());

                    ActionListener cbActionListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s = (String) options.getSelectedItem();
                            if (choices.length==3) {
                                switch (s) {
                                    case "Build":
                                        buildCase(player, cards, x);
                                        break;
                                    case "Sell":
                                        sellCase(player, x);
                                        break;
                                    case "Wonder":
                                        wonderCase(player, cards, x);
                                        break;

                                }
                            }else{
                                switch (s){
                                    case "Build":
                                        buildCase(player, cards, x);
                                        break;
                                    case "Sell":
                                        sellCase(player, x);
                                        break;
                                    case "Wonder":
                                        wonderCase(player, cards, x);
                                        break;
                                    case "BuildForFree":
                                        buildForFreeCase(player, cards, x);
                                        break;
                                }
                            }
                        }
                    };
                    options.addActionListener(cbActionListener);
                    button.add(options);
                    options.setOpaque(false);
                    options.setBackground(Color.BLACK);
                    cardPanel.paintAll(cardPanel.getGraphics()); //refreshes the panel
                }
            });
            button.setIcon(new ImageIcon(cards.get(i).getImage()));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(null);
            button.setOpaque(false);
            cardPanel.add(button);
            this.add(cardPanel, c);
            c.gridx++;
            cardPanel.setOpaque(false);
        }

        acceptButton = new AcceptButton(player, this);
        this.add(acceptButton, c);
        this.paintAll(this.getGraphics());

    }


    private String[] checkForBuildForFreeEffect(Player player) {
        ArrayList<Effect> wonderEffects = player.getWonderEffects();
        for (Effect wonderEffect1 : wonderEffects) {
            WonderEffect wonderEffect = (WonderEffect) wonderEffect1;
            if (wonderEffect.getEffectName().equals("BuildForFree") && !player.isUsedBuildForFreeEffect()) {
                return new String[]{"Build", "Sell", "Wonder", "BuildForFree"};
            }
        }
        return new String[]{"Build", "Sell", "Wonder"};
    }

    private void wonderCase(Player player, ArrayList<Card> cards, int x) {
        WonderStage wonderStageToBuild = player.getWonder().checkWhichWonderStageBuild();
        Set<Boolean> checkedIfCanBuildWonder = new HashSet<>(player.checkIfCanBuild(cards.get(x), wonderStageToBuild));
        if (checkedIfCanBuildWonder.contains(false)) {
            JOptionPane.showMessageDialog(CardsPanel.this,
                    "Cant Build, no required resources",
                    "Building warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            player.setAction(new UseForWonder(cards.get(x)));
            didChoose = true;
            acceptButton.setEnabled(true);
        }
    }

    private void sellCase(Player player, int x) {
        player.setAction(new Sell(x));
        didChoose = true;
        acceptButton.setEnabled(true);
    }

    private void buildCase(Player player, ArrayList<Card> cards, int x) {
        boolean canBuildFromCard = checkIfCanBuildFromCard(player, cards.get(x));
        if (canBuildFromCard){
            player.setAction(new Build(cards.get(x), false));
            didChoose = true;
            acceptButton.setEnabled(true);
        } else {
            Set<Boolean> checkedIfCanBuildBuilding = new HashSet<>(player.checkIfCanBuild(cards.get(x), null));
            if (checkedIfCanBuildBuilding.contains(false)) {
                JOptionPane.showMessageDialog(CardsPanel.this,
                        "Cant Build, no required resources",
                        "Building warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                player.setAction(new Build(cards.get(x), false));
                didChoose = true;
                acceptButton.setEnabled(true);
            }
        }
    }

    private boolean checkIfCanBuildFromCard(Player player, Card card) {
        ArrayList<String> canBuild = player.getCanBuildArray();
        for (String aCanBuild : canBuild) {
            if (aCanBuild.equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    private void buildForFreeCase(Player player, ArrayList<Card> cards, int x) {
        player.setAction(new Build(cards.get(x), true));
        didChoose=true;
        acceptButton.setEnabled(true);
    }
    public void disableCardPanel(){
        for (Component o : this.getComponents()) {
            o.setVisible(false);
        }
    }


}
