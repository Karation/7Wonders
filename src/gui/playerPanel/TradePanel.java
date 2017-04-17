package gui.playerPanel;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TradePanel extends JPanel implements ShowCardOnIcon{
    GridBagConstraints c = new GridBagConstraints();
    TradePanel(){
        this.setOpaque(false);
        this.setBorder(null);
        this.setLayout(new GridBagLayout());
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.PAGE_START;
        //this.setBorder(new Insets(0,0,0,0));

    }

    public void addTradeIcon(String tradeName, Card card){
        JLabel tradeLabel = new JLabel();
        tradeLabel.setIcon(new ImageIcon("Icons\\"+tradeName+".jpg"));
        tradeLabel.setBorder(null);

        MouseAdapter mouseAdapter = showCardsImage(card);
        tradeLabel.addMouseListener(mouseAdapter);
        this.add(tradeLabel, c);
        c.gridy++;
    }

    @Override
    public MouseAdapter showCardsImage(Card card) {

        return new MouseAdapter() {
            JFrame frame;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                frame=new JFrame();
                frame.getContentPane().add(new JLabel(new ImageIcon(card.getImage())));
                frame.setUndecorated(true);
                frame.setLocation(100, 300);
                frame.pack();
                frame.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.dispose();
            }
        };
    }
}
