package gui.playerPanel;

import cards.Card;
import cards.Science;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SciencePanel extends JPanel implements ShowCardOnIcon{
    private ScienceIconsPanel mathPanel = new ScienceIconsPanel();
    private ScienceIconsPanel scriptPanel = new ScienceIconsPanel();
    private ScienceIconsPanel mechaPanel = new ScienceIconsPanel();

    public SciencePanel(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        this.setBorder(null);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        this.add(mathPanel, c);

        c.gridy++;
        this.add(scriptPanel, c);

        c.gridy++;
        this.add(mechaPanel, c);

    }
    public void addScienceIcon(String scienceName, Science science) {
        JLabel scienceIcon = new JLabel();

        MouseAdapter mouseAdapter = showCardsImage(science);
        scienceIcon.addMouseListener(mouseAdapter);

        switch (scienceName) {
            case "Math":
                mathPanel.addIcon(scienceName, scienceIcon);
                break;
            case "Scripture":
                scriptPanel.addIcon(scienceName, scienceIcon);
                break;
            case "Mechanics":
                mechaPanel.addIcon(scienceName, scienceIcon);
        }

    }

    @Override
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

    class ScienceIconsPanel extends JPanel {
        private GridBagConstraints c = new GridBagConstraints();

        ScienceIconsPanel() {
            this.setOpaque(false);
            this.setBorder(null);
            this.setLayout(new GridBagLayout());
            c.gridy = 0;
            c.gridx = 0;
        }

        void addIcon(String scienceIconName, JLabel scienceIcon) {
            scienceIcon.setOpaque(false);
            scienceIcon.setBorder(null);
            scienceIcon.setIcon(new ImageIcon("Icons\\" + scienceIconName + ".jpg"));
            this.add(scienceIcon, c);
            c.gridx++;
        }
    }
}
