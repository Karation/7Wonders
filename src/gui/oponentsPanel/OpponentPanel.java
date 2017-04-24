package gui.oponentsPanel;

import player.Player;

import javax.swing.*;
import java.awt.*;


public class OpponentPanel extends JPanel {
    Player leftPlayer;
    Player rightPlayer;
    private  LeftOponentPanel leftOponentPanel;
    private RightOponentPanel rightOponentPanel;
    Box horizontalBox;
    public OpponentPanel(Player player){
        this.setLayout(new BorderLayout());
        leftPlayer = player.getLeftPlayer();
        rightPlayer=player.getRightPlayer();

//        this.setLayout(new GridBagLayout());
//        this.setOpaque(false);
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill=GridBagConstraints.LINE_START;
//        c.anchor=GridBagConstraints.LINE_START;
//        leftOponentPanel = new LeftOponentPanel(leftPlayer, player);
//        this.add(leftOponentPanel, c);
//
//        this.add(Box.createHorizontalGlue());
//
//        c.fill=GridBagConstraints.LINE_END;
//        c.anchor = GridBagConstraints.LINE_END;
//        rightOponentPanel = new RightOponentPanel(rightPlayer, player);
//        this.add(rightOponentPanel, c);
        horizontalBox=Box.createHorizontalBox();
        leftOponentPanel = new LeftOponentPanel(leftPlayer, player);
        horizontalBox.add(leftOponentPanel);
        horizontalBox.add(Box.createGlue());
        rightOponentPanel = new RightOponentPanel(rightPlayer, player);
        horizontalBox.add(rightOponentPanel);
        this.add(horizontalBox);
    }

    public LeftOponentPanel getLeftOponentPanel() {
        return leftOponentPanel;
    }

    public RightOponentPanel getRightOponentPanel() {
        return rightOponentPanel;
    }
}
