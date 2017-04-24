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
public class RightOponentPanel extends JPanel{

    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private MainWonderPanel mainWonderPanel;
    BuyPanel buyPanel;

    public RightOponentPanel(Player rightOpponent, Player player){
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        this.setBorder(null);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;

        buyPanel = new BuyPanel(rightOpponent, player, this);

        this.add(buyPanel, c);

        c.gridx=1;
        buildingsPanel = new BuildingsPanel();
        this.add(buildingsPanel, c);

        //c.gridx=1;
        //c.gridy=0;
        c.gridx=2;
        mainWonderPanel = new MainWonderPanel(rightOpponent.getWonder());
        this.add(mainWonderPanel, c);
//        ImageIcon wonderIcon = new ImageIcon(rightOpponent.getWonder().getImagePath());
//        JLabel wonderPicture=new JLabel(wonderIcon);
//        this.add(wonderPicture, c);

        c.gridy=1;
        c.gridx=2;
        c.anchor = GridBagConstraints.LINE_START;
        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel, c);
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
