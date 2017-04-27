package gui.oponentsPanel;

import player.Player;

import javax.swing.*;
import java.awt.*;


public class OpponentPanel extends JPanel {
    private  LeftOponentPanel leftOponentPanel;
    private RightOponentPanel rightOponentPanel;

    public OpponentPanel(Player player){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        Player leftPlayer = player.getLeftPlayer();
        Player rightPlayer = player.getRightPlayer();


        Box horizontalBox = Box.createHorizontalBox();
        leftOponentPanel = new LeftOponentPanel(leftPlayer, player);
        horizontalBox.add(leftOponentPanel);
        horizontalBox.add(Box.createHorizontalStrut(100));

        rightOponentPanel = new RightOponentPanel(rightPlayer, player);
        horizontalBox.add(rightOponentPanel);
        this.add(horizontalBox, BorderLayout.CENTER);


    }

    public LeftOponentPanel getLeftOponentPanel() {
        return leftOponentPanel;
    }

    public RightOponentPanel getRightOponentPanel() {
        return rightOponentPanel;
    }
}
