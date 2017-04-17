package gui.oponentsPanel;

import player.Player;

import javax.swing.*;
import java.awt.*;


public class OpponentPanel extends JPanel {
    Player leftPlayer;
    Player rightPlayer;
    private  LeftOponentPanel leftOponentPanel;
    private RightOponentPanel rightOponentPanel;
    public OpponentPanel(Player player){
        leftPlayer = player.getLeftPlayer();
        rightPlayer=player.getRightPlayer();
        BorderLayout borderLayout=new BorderLayout();
        borderLayout.setHgap(50);
        this.setLayout(borderLayout);
        this.setOpaque(false);

        leftOponentPanel = new LeftOponentPanel(leftPlayer, player);
        this.add(leftOponentPanel, BorderLayout.LINE_START);

        rightOponentPanel = new RightOponentPanel(rightPlayer, player);
        this.add(rightOponentPanel, BorderLayout.LINE_END);
    }

    public LeftOponentPanel getLeftOponentPanel() {
        return leftOponentPanel;
    }

    public RightOponentPanel getRightOponentPanel() {
        return rightOponentPanel;
    }
}
