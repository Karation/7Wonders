package gui;

import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by mkrec_000 on 20/03/2017.
 */
public class WonderChoiceThread extends Thread {
    ImageIcon wonderAIcon;
    ImageIcon wonderBIcon;
    Wonders wonder;
    boolean chosen=false;
    ArrayList<Wonders> wonderSides;
    ArrayList<Wonders> chosenWonders;

    public WonderChoiceThread(ArrayList<Wonders> wonderSides, ArrayList<Wonders> chosenWonders){
        this.wonderSides=wonderSides;
        this.chosenWonders=chosenWonders;
    }
    public void run() {
        chosen=false;
        JDialog wonderChoiceDialog = new JDialog();
        wonderAIcon=new ImageIcon(wonderSides.get(0).getImagePath());
        wonderBIcon=new ImageIcon(wonderSides.get(1).getImagePath());
        int x=wonderAIcon.getIconWidth()+wonderBIcon.getIconWidth()+10;
        int y=wonderAIcon.getIconHeight()+40;
        wonderChoiceDialog.setTitle("Choose Wonder");
        wonderChoiceDialog.setLayout(new GridLayout());
        wonderChoiceDialog.setSize(x,y);
        JButton wonderA = new JButton(wonderAIcon);
        wonderA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chosen){
                    chosenWonders.add(wonderSides.get(0));
                    chosen=true;
                }
            }
        });
        JButton wonderB = new JButton(wonderBIcon);
        wonderB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chosen){
                    chosenWonders.add(wonderSides.get(1));
                    chosen=true;
                }
            }
        });
        wonderChoiceDialog.add(wonderA);
        wonderChoiceDialog.add(wonderB);

        wonderChoiceDialog.setVisible(true);

    }
    public ArrayList<Wonders> getChosenWonders(){
        return chosenWonders;
    }
//    public Wonders getWonder() {
//        return wonder;
//    }
}
