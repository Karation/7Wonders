package gui.playerPanel;

import gui.playerPanel.TradePanel;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 21/03/2017.
 */
public class MainWonderPanel extends JLabel{
    private TradePanel tradePanel;
    private GuildPanel guildPanel;
    private WonderBuildPanel wonderBuildPanel;

    public MainWonderPanel(Wonders wonder){
        ImageIcon wonderPicture = new ImageIcon(wonder.getImagePath());
        this.setIcon(wonderPicture);
        this.setBorder(null);
        this.setLayout(new BorderLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.anchor = GridBagConstraints.FIRST_LINE_START;
//        c.gridx = 1;
//        c.gridy = 0;
//        c.gridwidth=1;
        tradePanel=new TradePanel();
        this.add(tradePanel, BorderLayout.CENTER);
//        c.gridx=2;
//        c.gridy = 0;
//        c.gridwidth=1;
        guildPanel = new GuildPanel();
        this.add(guildPanel, BorderLayout.LINE_END);
//        c.gridx = 0;
//        c.gridy=1;
//        c.gridwidth=3;
        wonderBuildPanel = new WonderBuildPanel(wonder.getWonderStages().size());
        wonderBuildPanel.setPreferredSize(new Dimension(this.getWidth(), 45));
        this.add(wonderBuildPanel, BorderLayout.PAGE_END);


    }
    public TradePanel getTradePanel() { return tradePanel; }

    public GuildPanel getGuildPanel() {
        return guildPanel;
    }

    public WonderBuildPanel getWonderBuildPanel() {
        return wonderBuildPanel;
    }
}
