package gui.playerPanel.wonderPanel.sidePanels.insidePanels;

import cards.Card;
import gui.playerPanel.wonderPanel.sidePanels.ShowCardOnIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuildPanel extends JPanel implements ShowCardOnIcon {
    private GridBagConstraints c = new GridBagConstraints();
    public GuildPanel(){
        this.setOpaque(false);
        this.setBorder(null);
        this.setLayout(new GridBagLayout());
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.PAGE_START;
    }
    public void addIcon(String guildName, Card card){
        JLabel guildLabel = new JLabel();
        guildLabel.setIcon(new ImageIcon("Icons\\guildIcons\\"+guildName+".jpg"));
        guildLabel.setBorder(null);

        MouseAdapter mouseAdapter = showCardsImage(card);
        guildLabel.addMouseListener(mouseAdapter);
        this.add(guildLabel, c);
        c.gridy++;
        if(c.gridy==4){
            c.gridy=0;
            c.gridx++;
        }
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
