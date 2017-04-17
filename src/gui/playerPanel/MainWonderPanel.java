package gui.playerPanel;

import gui.playerPanel.TradePanel;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mkrec_000 on 21/03/2017.
 */
public class MainWonderPanel extends JLabel{
    TradePanel tradePanel;
    public MainWonderPanel(Wonders wonder){
        ImageIcon wonderPicture = new ImageIcon(wonder.getImagePath());
        this.setIcon(wonderPicture);
        this.setBorder(null);
        this.setLayout(new FlowLayout());
        tradePanel=new TradePanel();
        this.add(tradePanel);
    }
    public TradePanel getTradePanel() { return tradePanel; }
}
