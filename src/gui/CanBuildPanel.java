package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 22/04/2017.
 */
public class CanBuildPanel extends JPanel{
    JList jList;
    DefaultListModel<String> model;
    JScrollPane pane;
    public CanBuildPanel() {
        model = new DefaultListModel<>();
        pane=new JScrollPane();
        jList=new JList(model);

        setOpaque(false);

        this.add(pane);

        pane.setOpaque(false);
        pane.setViewportView(jList);
        pane.getViewport().setOpaque(false);
        jList.setOpaque(false);
        pane.setPreferredSize(new Dimension(150, 200));
        this.setBounds(0,0,0,0);
        //((DefaultListCellRenderer) jList.getCellRenderer()).setOpaque(false);
    }
    public void addToList(String canBuild){
        if (canBuild!=null) {
            model.addElement(canBuild);
        }
        this.paintAll(this.getGraphics());
    }
}
