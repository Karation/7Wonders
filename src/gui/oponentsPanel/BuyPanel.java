package gui.oponentsPanel;

import effects.*;
import gui.oponentsPanel.BuyComboBox;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuyPanel extends JPanel{
    JButton buyButton;
    BuyComboBox buyComboBox;
    ArrayList<Effect> effects;
    public BuyPanel(Player opponent, Player player, JPanel opponentPanel){

        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        buyButton=new JButton();
        buyButton.setText("BUY");
        buyButton.setPreferredSize(new Dimension(100, 40));

        //this.setBorder(null);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        buyComboBox.removeElement(buyComboBox.getSelectedIndex());
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
            }
        });

        ArrayList<Effect> resourceEffects = new ArrayList<>(opponent.getResourceEffects());
        ArrayList<Effect> goodEffects = new ArrayList<>(opponent.getGoodEffects());
        System.out.println(opponent.getResourceEffects().size());
        System.out.println(opponent.getGoodEffects().size());
        System.out.println(player.getResourceEffects().size());
        System.out.println(player.getGoodEffects().size());
        System.out.println("");
        effects= new ArrayList<>(resourceEffects);
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
    public int checkForTradeEffects(Player player, int moneyToPay, Effect resourceOrGoodEffect, JPanel opponentPanel){
        ArrayList<Effect> tradeEffects = player.getTradeEffects();
        for (int i = 0; i < tradeEffects.size(); i++) {
            TradeEffect tradeEffect=(TradeEffect) tradeEffects.get(i);
            moneyToPay=tradeEffect.resolveTradeEffect(tradeEffect, resourceOrGoodEffect, opponentPanel);
        }
        return moneyToPay;
    }
    public void updatePlayer(Player player, int playerMoney, int moneyToPay, Effect resourceOrGoodEffect){
        ArrayList<Effect> boughtResourceEffects=player.getBoughtResourceEffects();
        ArrayList<Effect> boughtGoodEffects=player.getBoughtGoodEffects();
        playerMoney -= moneyToPay;
        player.setMoney(playerMoney);
        player.getPlayerPanel().getWonderPanel().getBuildingsPanel().updateMoneyIcon(playerMoney);

        if (resourceOrGoodEffect instanceof ResourceEffect||resourceOrGoodEffect instanceof MixedResourceEffect){
            boughtResourceEffects.add(resourceOrGoodEffect);
            player.setBoughtResourceEffects(boughtResourceEffects);
        }else if(resourceOrGoodEffect instanceof GoodEffect){
            boughtGoodEffects.add(resourceOrGoodEffect);
            player.setBoughtGoodEffects(boughtGoodEffects);
        }
    }
}
