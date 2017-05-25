package gui;

import board.Board;
import gui.playerPanel.CardsPanel;
import player.Player;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AcceptButton extends JToggleButton{
    private Boolean choice;
    //JPanel glassPane = new JPanel();
    public AcceptButton(Player player){
        this.setText("Accept");
        this.setEnabled(false);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getAction()!=null) {

                    choice = true;
                    AcceptButton.this.setEnabled(false);
                    Board board = player.getBoard();
                    board.addToChoicesArray(choice);
                    boolean didResolve = board.resolveActions();
                    if (didResolve) {
                        try {
                            board.nextTurn();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } finally {
                            board.clearChoicesArray();
                        }
                    }
                }
                choice=false;
            }
        });
    }

}
