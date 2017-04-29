package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 22/04/2017.
 */
public class ArmyPointsPanel extends JPanel {
    private GridBagConstraints c = new GridBagConstraints();
    ArmyIconsPanel army0 = new ArmyIconsPanel();
    ArmyIconsPanel army1 = new ArmyIconsPanel();
    ArmyIconsPanel army3 = new ArmyIconsPanel();
    ArmyIconsPanel army5 = new ArmyIconsPanel();

    public ArmyPointsPanel() {
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.anchor=GridBagConstraints.FIRST_LINE_END;
        this.add(army0, c);
        c.gridy = 1;
        this.add(army1, c);
        c.gridy = 2;
        this.add(army3, c);
        c.gridy = 3;
        this.add(army5, c);

    }

    public void addArmyIcon(String armyIconName) {

        switch (armyIconName) {
            case "Army-1":
                army0.addIcon(armyIconName);
                break;
            case "Army1":
                army1.addIcon(armyIconName);
                break;
            case "Army3":
                army3.addIcon(armyIconName);
                break;
            case "Army5":
                army5.addIcon(armyIconName);
                break;
        }

    }
}
class ArmyIconsPanel extends JPanel{
    GridBagConstraints c = new GridBagConstraints();
    ArmyIconsPanel(){
        this.setOpaque(false);
        this.setBorder(null);
        this.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;
    }
    void addIcon(String armyIconName){
        JLabel armyIcon = new JLabel();
        armyIcon.setOpaque(false);
        armyIcon.setBorder(null);
        armyIcon.setIcon(new ImageIcon("Icons\\"+armyIconName+".jpg"));
        this.add(armyIcon, c);
        c.gridx++;
    }
}
