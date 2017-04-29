package gui.playerPanel;

import cards.Card;
import gui.ArmyPointsPanel;
import wonders.Wonders;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class WonderPanel extends JPanel{
    ResourcesPanel resourcesPanel;
    BuildingsPanel buildingsPanel;
    MainWonderPanel mainWonderPanel;
    ArmyPointsPanel armyPointsPanel;


    public WonderPanel(Wonders wonder) throws IOException {
        this.setOpaque(false);
        this.setBorder(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        c.gridy=0;
        c.gridx=1;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel, c);

        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.FIRST_LINE_END;
        armyPointsPanel = new ArmyPointsPanel();
        this.add(armyPointsPanel, c);


        c.gridx=1;
        c.gridy=1;
        c.anchor = GridBagConstraints.CENTER;
        mainWonderPanel = new MainWonderPanel(wonder);
        this.add(mainWonderPanel, c);

        c.gridx++;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        buildingsPanel = new BuildingsPanel();
        this.add(buildingsPanel, c);


    }

    public ResourcesPanel getResourcesPanel() {
        return resourcesPanel;
    }

    public BuildingsPanel getBuildingsPanel() {
        return buildingsPanel;
    }

    public MainWonderPanel getMainWonderPanel() { return mainWonderPanel; }

    public ArmyPointsPanel getArmyPointsPanel() {
        return armyPointsPanel;
    }
}
