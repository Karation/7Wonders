package gui.oponentsPanel;

import gui.playerPanel.wonderPanel.sidePanels.*;
import player.Player;

import javax.swing.*;
import java.awt.*;

public class LeftOponentPanel extends JPanel{
    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private SciencePanel sciencePanel;
    private MainWonderPanel mainWonderPanel;
    private ArmyPointsLabels armyPointsPanel;
    private BuyPanel buyPanel;

    LeftOponentPanel(Player leftOpponent, Player player){

        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        this.setBorder(null);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx=0;
        c.gridy=0;
        c.gridheight=2;
        mainWonderPanel = new MainWonderPanel(leftOpponent.getWonder());
        this.add(mainWonderPanel, c);

        ImageIcon wonderIcon = new ImageIcon(leftOpponent.getWonder().getImagePath());
        JLabel wonderPicture=new JLabel(wonderIcon);
        this.add(wonderPicture, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight=2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        armyPointsPanel = new ArmyPointsLabels();
        this.add(armyPointsPanel, c);

        c.gridx=0;
        c.gridy=2;
        c.gridheight=1;
        c.anchor = GridBagConstraints.LINE_END;
        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel, c);

        c.gridx=1;
        c.gridy=0;
        c.gridheight=1;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        buildingsPanel = new BuildingsPanel();
        this.add(buildingsPanel, c);

        c.gridx=1;
        c.gridy=1;
        c.gridwidth=2;
        c.gridheight=1;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        sciencePanel=new SciencePanel();
        this.add(sciencePanel, c);


        c.gridx=2;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        buyPanel=new BuyPanel(leftOpponent, player, this);
        this.add(buyPanel, c);

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

    public ArmyPointsLabels getArmyPointsLabels() {
        return armyPointsPanel;
    }
}
