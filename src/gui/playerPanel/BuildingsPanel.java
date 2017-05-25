package gui.playerPanel;

import cards.Army;
import cards.Card;
import cards.Culture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BuildingsPanel extends JPanel implements ShowCardOnIcon {
    private JLabel armyLabel;
    private JLabel pointsLabel;
    private JLabel moneyLabel;
    private ArrayList<Card> armyCards = new ArrayList<>();
    private ArrayList<Card> cultureCards = new ArrayList<>();

    private int numOfShields = 0;
    private int numOfPoints = 0;
    private int money;
    private static final int STARTING_MONEY = 3;

    public BuildingsPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setOpaque(false);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;

        armyLabel = new JLabel("" + numOfShields);
        armyLabel.setIcon(new ImageIcon("Icons\\Army.jpg"));
        armyLabel.setHorizontalTextPosition(JLabel.CENTER);
        armyLabel.setForeground(Color.black);
        Font font = armyLabel.getFont();
        Font boltFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        armyLabel.setFont(boltFont);
        this.add(armyLabel, c);

        c.gridy++;
        pointsLabel = new JLabel("" + numOfPoints);
        pointsLabel.setIcon(new ImageIcon("Icons\\Points.jpg"));
        pointsLabel.setHorizontalTextPosition(JLabel.CENTER);
        pointsLabel.setForeground(Color.black);
        this.add(pointsLabel, c);

        c.gridy++;
        moneyLabel = new JLabel();
        moneyLabel.setIcon(new ImageIcon("Icons\\Money.jpg"));
        moneyLabel.setHorizontalTextPosition(JLabel.CENTER);
        moneyLabel.setText(String.valueOf(STARTING_MONEY));
        this.add(moneyLabel, c);

    }



    public void addArmyPoints(int shields, Card army) {
        numOfShields += shields;
        armyLabel.setText("" + numOfShields);

        armyCards.add(army);
        MouseAdapter mouseAdapter = showCardsImage(army);
        armyLabel.addMouseListener(mouseAdapter);
    }

    public void addCulturePoints(int points, Card culture) {
        numOfPoints += points;
        pointsLabel.setText("" + numOfPoints);

        cultureCards.add(culture);
        MouseAdapter mouseAdapter = showCardsImage(culture);
        pointsLabel.addMouseListener(mouseAdapter);
    }

    public void substractMoney(int amount) {
        moneyLabel.setText(String.valueOf(money - amount));
    }

    public void updateMoneyIcon(int playerMoney) {
        moneyLabel.setText("" + playerMoney);
    }

    @Override
    public MouseAdapter showCardsImage(Card card) {
        return new MouseAdapter() {
            JFrame frame;

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                frame = new JFrame();
                frame.setLayout(new FlowLayout());
                if (card instanceof Army) {
                    for (Card armyCard : armyCards) {
                        frame.getContentPane().add(new JLabel(new ImageIcon(armyCard.getImage())));
                    }
                } else if (card instanceof Culture) {
                    for (Card cultureCard : cultureCards) {
                        frame.getContentPane().add(new JLabel(new ImageIcon(cultureCard.getImage())));
                    }
                }
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


