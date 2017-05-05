package gui.playerPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 04/05/2017.
 */
public class WonderBuildPanel extends JPanel{
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel stage1;
    private JLabel stage2;
    private JLabel stage3;
    private JLabel stage4;
    WonderBuildPanel(int wonderStages){
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(null);

        createJLabelsForWonders(wonderStages);

    }
    public void addBuiltLabel(){

    }
    public void createJLabelsForWonders(int numOfStages){
        if (numOfStages == 3){
            this.add(Box.createHorizontalStrut(30));

            stage1 = new JLabel();
            stage1.setMaximumSize(new Dimension(115, 45));
            stage1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage1);

            this.add(Box.createHorizontalStrut(18));

            stage2 = new JLabel();
            stage2.setMaximumSize(new Dimension(115, 45));
            stage2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage2);

            this.add(Box.createHorizontalStrut(18));

            stage3 = new JLabel();
            stage3.setMaximumSize(new Dimension(115, 45));
            stage3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage3);

            this.add(Box.createHorizontalStrut(30));
        } else if (numOfStages==4){
            stage1 = new JLabel();
            stage1.setMaximumSize(new Dimension(100, 45));
            stage1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage1);

            this.add(Box.createHorizontalStrut(10));

            stage2 = new JLabel();
            stage2.setMaximumSize(new Dimension(110, 45));
            stage2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage2);

            this.add(Box.createHorizontalStrut(10));

            stage3 = new JLabel();
            stage3.setMaximumSize(new Dimension(110, 45));
            stage3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage3);

            this.add(Box.createHorizontalStrut(10));

            stage4 = new JLabel();
            stage4.setMaximumSize(new Dimension(100, 45));
            stage4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage4);
        }else if (numOfStages==2){
            this.add(Box.createHorizontalStrut(165));

            stage1 = new JLabel();
            stage1.setMaximumSize(new Dimension(115, 45));
            stage1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage1);

            this.add(Box.createHorizontalStrut(20));

            stage2 = new JLabel();
            stage2.setMaximumSize(new Dimension(115, 45));
            stage2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(stage2);

            this.add(Box.createHorizontalStrut(10));
        }
    }

}
