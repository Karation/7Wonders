package gui.oponentsPanel;

import gui.playerPanel.BuildingsPanel;
import gui.playerPanel.MainWonderPanel;
import gui.playerPanel.ResourcesPanel;
import player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 22/03/2017.
 */
public class LeftOponentPanel extends JPanel{
    ResourcesPanel resourcesPanel;
    BuildingsPanel buildingsPanel;
    MainWonderPanel mainWonderPanel;
    BuyPanel buyPanel;
    public LeftOponentPanel(Player leftOpponent, Player player){
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        this.setBorder(null);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;

        mainWonderPanel = new MainWonderPanel(leftOpponent.getWonder());
        this.add(mainWonderPanel, c);

        ImageIcon wonderIcon = new ImageIcon(leftOpponent.getWonder().getImagePath());
        JLabel wonderPicture=new JLabel(wonderIcon);
        this.add(wonderPicture, c);


        c.gridx=1;
        c.anchor=GridBagConstraints.LINE_START;
        //c.gridy=1;
        buildingsPanel = new BuildingsPanel();
        this.add(buildingsPanel, c);



        c.gridx=0;
        c.gridy=1;
        c.anchor = GridBagConstraints.LINE_END;
        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel, c);

        c.gridx=2;
        c.gridy=0;
        c.anchor=GridBagConstraints.PAGE_START;
        buyPanel=new BuyPanel(leftOpponent, player, this);
        this.add(buyPanel);

    }

    public BuildingsPanel getBuildingsPanel() {
        return buildingsPanel;
    }

    public ResourcesPanel getResourcesPanel() {
        return resourcesPanel;
    }

    public MainWonderPanel getMainWonderPanel() {
        return mainWonderPanel;
    }

    public void setBuyPanel(BuyPanel buyPanel) {
        this.buyPanel = buyPanel;
    }

    public BuyPanel getBuyPanel() {
        return buyPanel;
    }
}
