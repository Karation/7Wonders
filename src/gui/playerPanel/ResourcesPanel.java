package gui.playerPanel;

import cards.Card;
import cards.Good;
import cards.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class ResourcesPanel  extends JPanel implements ShowCardOnIcon{
    JPanel resourcesPanel;
    JPanel goodsPanel;
    public ResourcesPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setOpaque(false);
        this.setBackground(Color.red);
        this.setBorder(null);
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.LINE_START;
        resourcesPanel = new JPanel();
        goodsPanel = new JPanel();

        resourcesPanel.setLayout(new GridLayout());
        resourcesPanel.setOpaque(false);
        resourcesPanel.setBorder(null);
        this.add(resourcesPanel, c);

        c.gridx++;
        c.anchor = GridBagConstraints.LINE_END;
        goodsPanel.setLayout(new GridLayout());
        goodsPanel.setOpaque(false);
        goodsPanel.setBorder(null);
        this.add(goodsPanel, c);


        this.setOpaque(false);
        this.setBorder(null);
    }
    public void addResourceIcon(String resourceName, Resource resource){
        JLabel resourceIcon = new JLabel();
        resourceIcon.setIcon(new ImageIcon("Icons\\"+resourceName+".jpg"));
        resourceIcon.setOpaque(false);
        resourceIcon.setBorder(null);
        resourceIcon.setSize(50,50);
        resourceIcon.setLocation(0,0);

        MouseAdapter mouseAdapter = showCardsImage(resource);
        resourceIcon.addMouseListener(mouseAdapter);

        resourcesPanel.setBorder(null);
        resourcesPanel.add(resourceIcon);

    }


    public void addGoodsIcon(String goodName, Good good){
        JLabel goodIcon = new JLabel();
        goodIcon.setIcon(new ImageIcon("Icons\\"+goodName+".jpg"));
        goodIcon.setOpaque(false);
        goodIcon.setBorder(null);

        MouseAdapter mouseAdapter = showCardsImage(good);
        goodIcon.addMouseListener(mouseAdapter);

        goodsPanel.add(goodIcon);
    }
    public MouseAdapter showCardsImage(Card card) {

        return new MouseAdapter() {
            JFrame frame;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                frame = new JFrame();
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
