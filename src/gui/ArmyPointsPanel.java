package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 22/04/2017.
 */
public class ArmyPointsPanel extends JPanel{
    public ArmyPointsPanel() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout());
    }

    public void addArmyIcon(String armyIconName){
        JLabel armyIcon = new JLabel();
        armyIcon.setIcon(new ImageIcon("Icons\\"+armyIconName+".jpg"));
        armyIcon.setOpaque(false);
        armyIcon.setBorder(null);
        this.add(armyIcon);
    }
}
