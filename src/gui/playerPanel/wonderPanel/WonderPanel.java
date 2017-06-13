package gui.playerPanel.wonderPanel;


import gui.playerPanel.wonderPanel.sidePanels.*;
import wonders.Wonders;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WonderPanel extends JPanel{
    private ResourcesPanel resourcesPanel;
    private BuildingsPanel buildingsPanel;
    private MainWonderPanel mainWonderPanel;
    private ArmyPointsPanel armyPointsPanel;
    private SciencePanel sciencePanel;


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
        c.gridheight=2;
        c.anchor = GridBagConstraints.CENTER;
        mainWonderPanel = new MainWonderPanel(wonder);
        this.add(mainWonderPanel, c);

        c.gridy=1;
        c.gridx++;
        c.gridheight=1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        buildingsPanel = new BuildingsPanel();
        this.add(buildingsPanel, c);

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridy=2;
        sciencePanel = new SciencePanel();
        this.add(sciencePanel, c);



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

    public SciencePanel getSciencePanel() {
        return sciencePanel;
    }
}
