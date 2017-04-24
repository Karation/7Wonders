package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 22/04/2017.
 */
public class CanBuildPanel extends JPanel{
    JList jList;
    DefaultListModel<String> model;
    public CanBuildPanel() {
        model = new DefaultListModel<>();
        jList=new JList(model);
        //this.setOpaque(false);
        this.add(jList);
        jList.setBackground(new Color(30));
        jList.setEnabled(false);
        this.setSize(100, 300);
        this.setVisible(true);

    }
    public void addToList(String canBuild){
        model.addElement(canBuild);
        this.paintAll(this.getGraphics());
    }
}
