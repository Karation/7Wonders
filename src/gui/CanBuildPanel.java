package gui;

import javax.swing.*;
import java.awt.*;

public class CanBuildPanel extends JPanel{
    private DefaultListModel<String> model;

    public CanBuildPanel() {
        model = new DefaultListModel<>();
        JScrollPane pane = new JScrollPane();
        JList jList = new JList(model);

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
