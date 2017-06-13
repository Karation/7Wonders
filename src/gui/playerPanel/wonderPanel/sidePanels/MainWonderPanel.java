package gui.playerPanel.wonderPanel.sidePanels;


import gui.playerPanel.wonderPanel.sidePanels.insidePanels.GuildPanel;
import gui.playerPanel.wonderPanel.sidePanels.insidePanels.TradePanel;
import gui.playerPanel.wonderPanel.sidePanels.insidePanels.WonderBuildPanel;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWonderPanel extends JLabel{
    private TradePanel tradePanel;
    private GuildPanel guildPanel;
    private WonderBuildPanel wonderBuildPanel;

    public MainWonderPanel(Wonders wonder){
        ImageIcon wonderPicture = new ImageIcon(wonder.getImagePath());
        this.setIcon(wonderPicture);
        this.setBorder(null);
        this.setLayout(new BorderLayout());

        tradePanel=new TradePanel();
        this.add(tradePanel, BorderLayout.CENTER);

        guildPanel = new GuildPanel();
        this.add(guildPanel, BorderLayout.LINE_END);

        try {
            wonderBuildPanel = new WonderBuildPanel(wonder.getWonderStages().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
