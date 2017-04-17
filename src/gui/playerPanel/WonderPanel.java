package gui.playerPanel;

import cards.Card;
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
    JFrame frame;


    public WonderPanel(Wonders wonder) throws IOException {
        this.setOpaque(false);
        this.setBorder(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        c.gridy=0;
        c.gridx=0;
        c.anchor=GridBagConstraints.LINE_END;

        resourcesPanel = new ResourcesPanel();
        this.add(resourcesPanel, c);
        c.gridx++;
        c.anchor = GridBagConstraints.CENTER;

        mainWonderPanel = new MainWonderPanel(wonder);
        this.add(mainWonderPanel, c);

        c.gridx++;
        c.anchor = GridBagConstraints.LINE_START;
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

}
