package gui.oponentsPanel;

import gui.playerPanel.BuildingsPanel;
import gui.playerPanel.MainWonderPanel;
import gui.playerPanel.ResourcesPanel;
import gui.playerPanel.SciencePanel;
import player.Player;

import javax.swing.*;
import java.awt.*;

public class RightOponentPanel extends JPanel{

    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private MainWonderPanel mainWonderPanel;
    private SciencePanel sciencePanel;
    private BuyPanel buyPanel;

    RightOponentPanel(Player rightOpponent, Player player){
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        this.setBorder(null);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.anchor=GridBagConstraints.FIRST_LINE_END;
        buyPanel = new BuyPanel(rightOpponent, player, this);
        this.add(buyPanel, c);

        c.gridx=1;
        c.gridy=0;
        c.gridheight=1;
        c.gridwidth=1;
        buildingsPanel = new BuildingsPanel();
        this.add(buildingsPanel, c);

        c.gridx=0;
        c.gridy=1;
        c.gridheight=1;
        c.gridwidth=2;
        c.anchor=GridBagConstraints.FIRST_LINE_END;
        sciencePanel=new SciencePanel();

        this.add(sciencePanel, c);

        c.gridx=2;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=2;
        mainWonderPanel = new MainWonderPanel(rightOpponent.getWonder());
        this.add(mainWonderPanel, c);

        c.gridy=2;
        c.gridx=2;
        c.gridheight=1;
        c.gridwidth=1;
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

    public SciencePanel getSciencePanel() {
        return sciencePanel;
    }
}
