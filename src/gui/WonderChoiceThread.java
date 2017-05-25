package gui;

import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WonderChoiceThread extends Thread {
    private ImageIcon wonderAIcon;
    private ImageIcon wonderBIcon;
    private boolean chosen=false;
    private ArrayList<Wonders> wonderSides;
    private ArrayList<Wonders> chosenWonders;

    public WonderChoiceThread(ArrayList<Wonders> wonderSides, ArrayList<Wonders> chosenWonders){
        this.wonderSides=wonderSides;
        this.chosenWonders=chosenWonders;
    }
    public void run() {
        chosen=false;
        JDialog wonderChoiceDialog = new JDialog();
        createWonderIcons(wonderChoiceDialog);
        JButton wonderA = new JButton(wonderAIcon);
        wonderA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chosen) {
                    chosenWonders.add(wonderSides.get(0));
                    chosen = true;
                    wonderChoiceDialog.dispose();
                }
            }
        });
        JButton wonderB = new JButton(wonderBIcon);
        wonderB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chosen) {
                    chosenWonders.add(wonderSides.get(1));
                    chosen = true;
                    wonderChoiceDialog.dispose();
                }
            }
        });
        wonderChoiceDialog.add(wonderA);
        wonderChoiceDialog.add(wonderB);

        wonderChoiceDialog.setVisible(true);

    }

    private void createWonderIcons(JDialog wonderChoiceDialog) {
        wonderAIcon=new ImageIcon(wonderSides.get(0).getImagePath());
        wonderBIcon=new ImageIcon(wonderSides.get(1).getImagePath());
        int x=wonderAIcon.getIconWidth()+wonderBIcon.getIconWidth()+10;
        int y=wonderAIcon.getIconHeight()+40;
        wonderChoiceDialog.setTitle("Choose Wonder");
        wonderChoiceDialog.setLayout(new GridLayout());
        wonderChoiceDialog.setSize(x,y);
    }

//    public ArrayList<Wonders> getChosenWonders(){
//        return chosenWonders;
//    }
//    public Wonders getWonder() {
//        return wonder;
//    }
}
