package gui.oponentsPanel;

import effects.*;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BuyPanel extends JPanel{
    private BuyComboBox buyComboBox;

    public BuyPanel(Player opponent, Player player, JPanel opponentPanel){

        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JButton buyButton = new JButton();
        buyButton.setText("BUY");
        buyButton.setPreferredSize(new Dimension(100, 40));

        buyButton.addActionListener(e -> {
            ImagesAndText imagesAndText = (ImagesAndText) buyComboBox.getSelectedItem();

            if(imagesAndText!=null) {
                int moneyToPay=2;
                int playerMoney=player.getMoney();
                ImagesAndText selectedElement= (ImagesAndText) buyComboBox.getSelectedItem();
                Effect resourceOrGoodEffect=selectedElement.getResourceOrGoodEffect();
                moneyToPay=checkForTradeEffects(player, moneyToPay, resourceOrGoodEffect, opponentPanel);
                if (moneyToPay>playerMoney){
                    JOptionPane.showMessageDialog(BuyPanel.this,
                            "Not enough money",
                            "Buying Warning",
                            JOptionPane.WARNING_MESSAGE);
                }else {
                    if (resourceOrGoodEffect instanceof DoubleResourceEffect){
                        DoubleResourceEffect doubleResourceEffect = (DoubleResourceEffect) resourceOrGoodEffect;
                        if (doubleResourceEffect.getNumberOfResources() == 2){
                            doubleResourceEffect.setNumberOfResources(1);
                        }else{
                            buyComboBox.removeElement(buyComboBox.getSelectedIndex());
                        }
                    }else{
                        buyComboBox.removeElement(buyComboBox.getSelectedIndex());
                    }
                    updatePlayer(player, playerMoney, moneyToPay, resourceOrGoodEffect);
                    opponent.payOpponentForBoughtItem(moneyToPay);

                }
                System.out.println(player.getMoney());

            }
            else{
                JOptionPane.showMessageDialog(BuyPanel.this,
                        "Cant buy any other resources or goods",
                        "Buying Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        ArrayList<Effect> resourceEffects = new ArrayList<>(opponent.getResourceEffects());
        ArrayList<Effect> goodEffects = new ArrayList<>(opponent.getGoodEffects());
        ArrayList<Effect> effects = new ArrayList<>(resourceEffects);
        effects.addAll(goodEffects);


        buyComboBox=new BuyComboBox(effects);
        buyComboBox.setPreferredSize(new Dimension(100,60));

        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.PAGE_START;
        this.add(buyButton, c);
        c.gridy=1;
        this.add(buyComboBox, c);
        this.paintAll(this.getGraphics());

    }
    private int checkForTradeEffects(Player player, int moneyToPay, Effect resourceOrGoodEffect, JPanel opponentPanel){
        ArrayList<Effect> tradeEffects = player.getTradeEffects();
        for (Effect tradeEffect1 : tradeEffects) {
            TradeEffect tradeEffect = (TradeEffect) tradeEffect1;
            moneyToPay = tradeEffect.resolveTradeEffect(tradeEffect, resourceOrGoodEffect, opponentPanel);
            if (moneyToPay == 1) {
                return moneyToPay;
            }
        }
        return moneyToPay;
    }
    private void updatePlayer(Player player, int playerMoney, int moneyToPay, Effect resourceOrGoodEffect){
        ArrayList<Effect> boughtResourceEffects=player.getBoughtResourceEffects();
        ArrayList<Effect> boughtGoodEffects=player.getBoughtGoodEffects();
        playerMoney -= moneyToPay;
        player.setMoney(playerMoney);
        player.getPlayerPanel().getWonderPanel().getBuildingsPanel().updateMoneyIcon(playerMoney);

        if (resourceOrGoodEffect instanceof ResourceEffect||resourceOrGoodEffect instanceof MixedResourceEffect||resourceOrGoodEffect instanceof DoubleResourceEffect){
            boughtResourceEffects.add(resourceOrGoodEffect);
            player.setBoughtResourceEffects(boughtResourceEffects);
        }else if(resourceOrGoodEffect instanceof GoodEffect){
            boughtGoodEffects.add(resourceOrGoodEffect);
            player.setBoughtGoodEffects(boughtGoodEffects);
        }
    }
}
