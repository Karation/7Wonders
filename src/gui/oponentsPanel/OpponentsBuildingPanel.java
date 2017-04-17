package gui.oponentsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 31/03/2017.
 */
public class OpponentsBuildingPanel extends JPanel{
    JLabel pointsLabel;
    JLabel armyLabel;
    JLabel moneyLabel;
    OpponentsBuildingPanel(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;

        armyLabel = new JLabel();
        ImageIcon armyIcon = new ImageIcon("Icons\\Army.jpg");
        armyLabel.setIcon(armyIcon);
        armyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        armyLabel.setVerticalTextPosition(SwingConstants.TOP);
        this.add(armyLabel, c);

        c.gridy++;
        pointsLabel = new JLabel();
        pointsLabel.setIcon(new ImageIcon("Icons\\Points.jpg"));
        pointsLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.add(pointsLabel, c);

        c.gridy++;
        moneyLabel=new JLabel();
        moneyLabel.setIcon(new ImageIcon("Icons\\Money.jpg"));
        moneyLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.add(moneyLabel, c);
    }
}
