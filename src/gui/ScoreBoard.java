package gui;

import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreBoard extends JFrame {
    private JPanel scorePanel;
    private JLabel player;
    private JLabel army;
    private JLabel money;
    private JLabel wonder;
    private JLabel culture;
    private JLabel trade;
    private JLabel guilds;
    private JLabel science;
    private JLabel sumPoints;

    public ScoreBoard(ArrayList<Player> players) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(0,9,0,0));
        createLabels();
        scorePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        scorePanel.add(player); scorePanel.add(army); scorePanel.add(money);
        scorePanel.add(wonder); scorePanel.add(culture); scorePanel.add(trade);
        scorePanel.add(guilds); scorePanel.add(science); scorePanel.add(sumPoints);
        for (Player player : players) {
            player.calculatePoints();
        }
        for (Player player : players) {
            createTextFields(player);
        }
        this.add(scorePanel);
        this.setLocationByPlatform(true);
        this.pack();
        this.setVisible(true);
    }

    private void createLabels() {
        player = new JLabel("Player");
        army = new JLabel("Army");
        money = new JLabel("Money");
        wonder = new JLabel("Wonder");
        culture = new JLabel("Culture");
        trade = new JLabel("Trade");
        guilds = new JLabel("Guilds");
        science = new JLabel("Science");
        sumPoints = new JLabel("Summary");
    }

    private void createTextFields(Player player) {
        ArrayList<Float> points = player.getOverallPoints();
        int sum=0;
        JTextArea playerName = new JTextArea(player.getName());
        JTextArea armyPoints = new JTextArea(String.valueOf(points.get(0)));
        JTextArea moneyPoints = new JTextArea(String.valueOf(points.get(1)));
        JTextArea wonderPoints = new JTextArea(String.valueOf(points.get(2)));
        JTextArea culturePoints = new JTextArea(String.valueOf(points.get(3)));
        JTextArea tradePoints = new JTextArea(String.valueOf(points.get(4)));
        JTextArea guildsPoints = new JTextArea(String.valueOf(points.get(5)));
        JTextArea sciencePoints = new JTextArea(String.valueOf(points.get(6)));
        for (Float point : points) {
            sum+=point;
        }
        JTextArea summaryPoints = new JTextArea(String.valueOf(sum));

        scorePanel.add(playerName);
        scorePanel.add(armyPoints);
        scorePanel.add(moneyPoints);
        scorePanel.add(wonderPoints);
        scorePanel.add(culturePoints);
        scorePanel.add(tradePoints);
        scorePanel.add(guildsPoints);
        scorePanel.add(sciencePoints);
        scorePanel.add(summaryPoints);
    }
}
