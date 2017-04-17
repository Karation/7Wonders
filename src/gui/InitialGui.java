package gui;

import cards.Card;
import gui.oponentsPanel.OpponentPanel;
import gui.playerPanel.PlayerPanel;
import player.Player;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class InitialGui {
    private static final String BACKGROUND_IMAGE_PATH = "wondersWallpaper.jpg";
    PlayerPanel playerPanel;

    public InitialGui(Player player, ArrayList<Card> cards, Wonders wonder) throws IOException{
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        final ImageIcon backgroundImage=new ImageIcon(BACKGROUND_IMAGE_PATH);
        JLabel mainPanel=new JLabel(backgroundImage){
            @Override
            public Dimension getPreferredSize(){
                Dimension size = super.getPreferredSize();
                Dimension imagePrefSize=getLayout().preferredLayoutSize(this);
                size.width=Math.max(size.width, imagePrefSize.width);
                size.width=Math.max(size.height, imagePrefSize.height);
                return size;
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c =new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.PAGE_END;
        OpponentPanel opponentPanel=new OpponentPanel(player);
        mainPanel.add(opponentPanel, c);
        player.setOpponentPanel(opponentPanel);

        c.gridy++;
        c.weighty=1;
        c.anchor=GridBagConstraints.PAGE_END;
        playerPanel=player.start(mainPanel, c, cards, wonder);

        frame.add(mainPanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);

    }
}
