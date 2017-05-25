package board;

import cards.*;
import effects.*;
import gui.ScoreBoard;
import gui.oponentsPanel.*;
import gui.optionalPanels.CardsInGraveChoiceDialog;
import gui.optionalPanels.UseLastCardDialog;
import gui.playerPanel.BuildingsPanel;
import gui.playerPanel.ResourcesPanel;
import gui.playerPanel.SciencePanel;
import gui.playerPanel.TradePanel;
import player.Player;
import player.action.Action;
import player.action.Build;
import player.action.Sell;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private ArrayList<Player> players;
    private List<Boolean> choices;
    private ArrayList<Card> cardsInGrave = new ArrayList<>();
    private int age = 1;
    private final static String FILEPATH_2 ="cards2.txt";
    private final static String FILEPATH_3 ="cards3.txt";
    private final static String GUILDS ="guilds.txt";

    public Board(ArrayList<Player> players) {
        this.players = players;
        choices = new ArrayList<>(players.size());
    }

//    public Player getPlayer(int i) {
//        return players.get(i);
//    }

    public void addToChoicesArray(Boolean choice) {
        choices.add(choice);
    }

    public void clearChoicesArray() {
        choices.clear();
    }

    public boolean resolveActions() {
        Set<Boolean> flags = new HashSet<>(choices);
        for (Player player1 : players) { //Check for useLastCardEffect
            if (player1.getCardsInHand().size() == 1) {
                player1.startAction();
                updateOpponentsPanel();
                return true;
            }
        }
        if (choices.size() == players.size()) {
            if (flags.contains(false)) {
                return false;
            }
            for (Player player : players) {
                player.startAction();
            }
            for (int i = 0; i < players.size(); i++) {
                addMoneyForBoughtItems(i);
            }
            updateOpponentsPanel();

            return true;
        }
        return false;
    }

    private void updateOpponentsPanel() {
        for (Player player : players) {
            Player leftPlayer = player.getLeftPlayer();
            Player rightPlayer = player.getRightPlayer();

            Card leftPlayerCard = leftPlayer.getCardToBeRemoved();
            Card rightPlayerCard = rightPlayer.getCardToBeRemoved();

            LeftOponentPanel leftOponentPanel = player.getOpponentPanel().getLeftOponentPanel();
            RightOponentPanel rightOponentPanel = player.getOpponentPanel().getRightOponentPanel();

            BuildingsPanel leftOpponentBuildingPanel = leftOponentPanel.getBuildingsPanel();
            BuildingsPanel rightOpponentBuildingPanel = rightOponentPanel.getBuildingsPanel();

            ResourcesPanel leftOpponentResourcePanel = leftOponentPanel.getResourcesPanel();
            ResourcesPanel rightOpponentResourcePanel = rightOponentPanel.getResourcesPanel();

            TradePanel leftTradePanel = leftOponentPanel.getMainWonderPanel().getTradePanel();
            TradePanel rightTradePanel = rightOponentPanel.getMainWonderPanel().getTradePanel();

            SciencePanel leftSciencePanel = leftOponentPanel.getSciencePanel();
            SciencePanel rightSciencePanel = rightOponentPanel.getSciencePanel();

            Action leftPlayerAction = leftPlayer.getAction();
            Action rightPlayerAction = rightPlayer.getAction();

            if (leftPlayerAction instanceof Build) {
                addIconToOpponentPanel(player, leftPlayerCard, leftOpponentBuildingPanel, leftOpponentResourcePanel, leftTradePanel, leftSciencePanel);
            } else if (leftPlayerAction instanceof Sell) {
                addMoneyToOpponentPanelFromSell(leftPlayer, leftOpponentBuildingPanel);
            }

            if (rightPlayerAction instanceof Build) {
                addIconToOpponentPanel(player, rightPlayerCard, rightOpponentBuildingPanel, rightOpponentResourcePanel, rightTradePanel, rightSciencePanel);
            } else if (rightPlayerAction instanceof Sell) {
                addMoneyToOpponentPanelFromSell(rightPlayer, rightOpponentBuildingPanel);
            }
            addMoneyToOpponentPanel(leftPlayer, leftOpponentBuildingPanel);
            addMoneyToOpponentPanel(rightPlayer, rightOpponentBuildingPanel);
            updateBuyPanel(leftPlayer, rightPlayer, player, leftOponentPanel, rightOponentPanel);
            player.getOpponentPanel().paintAll(player.getOpponentPanel().getGraphics());
        }
    }

    private void updateBuyPanel(Player leftOpponent, Player rightOpponent, Player player, LeftOponentPanel leftOponentPanel, RightOponentPanel rightOponentPanel) {
        GridBagConstraints c = new GridBagConstraints();

        BuyPanel leftBuyPanel = new BuyPanel(leftOpponent, player, leftOponentPanel);
        BuyPanel rightBuyPanel = new BuyPanel(rightOpponent, player, rightOponentPanel);

        leftOponentPanel.remove(leftOponentPanel.getBuyPanel());
        rightOponentPanel.remove(rightOponentPanel.getBuyPanel());

        c.gridx=2;
        c.gridy=0;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        leftOponentPanel.add(leftBuyPanel, c);
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        rightOponentPanel.add(rightBuyPanel, c);
        leftOponentPanel.setBuyPanel(leftBuyPanel);
        rightOponentPanel.setBuyPanel(rightBuyPanel);


    }

    private void addIconToOpponentPanel(Player player, Card card, BuildingsPanel opponentsBuildingPanel, ResourcesPanel resourcesPanel, TradePanel tradePanel, SciencePanel sciencePanel) {

        JLabel backgroundPanel = player.getBackgroundPanel();

        switch (card.getType()) {
            case "Resource":
                String resourceName;
                Resource resource = (Resource) card;
                if (resource.getResourceEffect() instanceof MixedResourceEffect) {
                    MixedResourceEffect mixedResourceEffect = (MixedResourceEffect) resource.getResourceEffect();
                    resourceName = mixedResourceEffect.getEffectName();
                    opponentsBuildingPanel.substractMoney(1);
                } else if(resource.getResourceEffect() instanceof DoubleResourceEffect){
                    DoubleResourceEffect doubleResourceEffect = (DoubleResourceEffect) resource.getResourceEffect();
                    resourceName = doubleResourceEffect.getEffectName();
                    opponentsBuildingPanel.substractMoney(1);
                } else {
                    ResourceEffect resourceEffect = (ResourceEffect) resource.getResourceEffect();
                    resourceName = resourceEffect.getResourceType();
                }
                resourcesPanel.addResourceIcon(resourceName, resource);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());
                break;
            case "Good":
                Good good = (Good) card;
                GoodEffect goodEffect = (GoodEffect) good.getGoodEffect();
                String goodName = goodEffect.getGoodType();
                resourcesPanel.addGoodsIcon(goodName, good);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());
                break;
            case "Army":
                Army army = (Army) card;
                ArmyEffect armyEffect = (ArmyEffect) army.getArmyEffect();
                int numOfShields = armyEffect.getNumOfShields();
                opponentsBuildingPanel.addArmyPoints(numOfShields, army);
                break;
            case "Culture":
                Culture culture = (Culture) card;
                CultureEffect cultureEffect = (CultureEffect) culture.getCultureEffect();
                int numOfPoints = cultureEffect.getNumOfPoints();
                opponentsBuildingPanel.addCulturePoints(numOfPoints, culture);
                break;
            case "Science":
                Science science = (Science) card;
                ScienceEffect scienceEffect = (ScienceEffect) science.getScienceEffect();
                String symbol = scienceEffect.getScienceSymbol();
                sciencePanel.addScienceIcon(symbol, science);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());
                break;
            case "Trade":
                Trade trade = (Trade) card;
                TradeEffect tradeEffect = (TradeEffect) trade.getTradeEffect();
                String emblem = tradeEffect.getEmblem();
                tradePanel.addTradeIcon(emblem, trade);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());
                break;
        }
    }

    private void addMoneyToOpponentPanel(Player opponent, BuildingsPanel buildingsPanel) {
        int money = opponent.getMoney();
        buildingsPanel.updateMoneyIcon(money);
    }

    private void addMoneyToOpponentPanelFromSell(Player opponent, BuildingsPanel buildingsPanel) {
        int money = opponent.getMoney();
        money += 3;
        buildingsPanel.updateMoneyIcon(money);
    }

    private void addMoneyForBoughtItems(int i) {
        Player player = players.get(i);
        int moneyForBoughtItems = player.getMoneyToBeTransfered();
        int money = player.getMoney();
        money += moneyForBoughtItems;
        player.setMoney(money);
        money = 0;
        player.setMoneyToBeTransfered(money);
        player.getPlayerPanel().getWonderPanel().getBuildingsPanel().updateMoneyIcon(money);
    }

    public void nextTurn() throws IOException {
        for (Player player : players) {
            player.setCopiedCardsInHand(player.getCardsInHand());
            player.clearBoughtGoodAndResourceEffects();
        }
        for (Player player : players) {
            if (player.getCardsInHand().size() > 1) {
                player.nextTurn();
            }
        }
        if (players.get(0).getCardsInHand().size() < 2) {
            checkForUseLastCardEffect();
            System.out.println("next age");
            this.nextAge();
        }
    }

    private void checkForUseLastCardEffect() {
        for (Player player : players) {
            ArrayList<Effect> wonderEffects = player.getWonderEffects();
            for (Effect effect : wonderEffects) {
                WonderEffect wonderEffect = (WonderEffect) effect;
                if (wonderEffect.getEffectName().equals("UseLastCard")) {
                    UseLastCardDialog dialog = new UseLastCardDialog(player, player.getRightPlayer());
                    //boolean chosen = dialog.createDialog();
                    dialog.createDialog();
                    //while (!chosen) ;
                }
            }
        }
    }

    private void nextAge() {
        for (Player player : players) {
            cardsInGrave.addAll(player.getCardsInHand());
            player.getPlayerPanel().remove(player.getPlayerPanel().getCardsPanel());
            player.getCardsInHand().clear();
            player.getBackgroundPanel().paintAll(player.getBackgroundPanel().getGraphics());
        }
        checkForBuildFromGraveEffect(cardsInGrave);
        this.resoveArmyEffects(players);

        age++;

        try {
            Deck deck = new Deck();
            if (age==2) {
                deck.dealCards(deck.shuffle(deck.loadCards(FILEPATH_2)), players);
            }else if(age==3){
                ArrayList<Card> cardsAge3 = deck.loadCards(FILEPATH_3);
                ArrayList<Card> guilds = deck.loadCards(GUILDS);
                deck.dealCards(deck.addGuildsToDeck(guilds, cardsAge3, players.size()), players);
            }else if(age==4){
                endGame();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Player player : players) {
            try {
                player.nextAge();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void endGame() {
       // ScoreBoard scoreFrame;
        //for (Player player : players) {
            new ScoreBoard(players);
        //}
    }

    private void checkForBuildFromGraveEffect(ArrayList<Card> cardsInGrave) {
        for (Player player : players) {
            ArrayList<Effect> wonderEffects = player.getWonderEffects();
            for (Effect effect : wonderEffects) {
                WonderEffect wonderEffect = (WonderEffect) effect;
                if (wonderEffect.getEffectName().equals("BuildFromGrave")) {
                    CardsInGraveChoiceDialog dialog = new CardsInGraveChoiceDialog(cardsInGrave, player);
                    dialog.createDialog();
                } else {
                    player.setAction(null);
                }
            }
        }
        //updateOpponentsPanel();
    }

    private void resoveArmyEffects(ArrayList<Player> players) {

        for (Player player : players) {
            int numOfshields = 0;
            ArrayList<Effect> effects = player.getArmyEffects();
            for (Effect effect : effects) {
                ArmyEffect armyEffect = (ArmyEffect) effect;
                numOfshields += armyEffect.getNumOfShields();
            }
            player.setNumOfShields(numOfshields);
        }
        for (Player player : players) {
            Player leftPlayer = player.getLeftPlayer();
            Player rightPlayer = player.getRightPlayer();
            resolveArmyPoints(player, leftPlayer);
            resolveArmyPoints(player, rightPlayer);
        }
    }

    private void resolveArmyPoints(Player player, Player opponent) {
        if (player.getNumOfShields() > opponent.getNumOfShields()) {
            int positiveArmyPoints = player.getPositiveArmyPoints();
            switch (age) {
                case 1:
                    positiveArmyPoints += 1;
                    player.setPositiveArmyPoints(positiveArmyPoints);
                    player.getPlayerPanel().getWonderPanel().getArmyPointsPanel().addArmyIcon("Army1");
                    break;
                case 2:
                    positiveArmyPoints += 3;
                    player.setPositiveArmyPoints(positiveArmyPoints);
                    player.getPlayerPanel().getWonderPanel().getArmyPointsPanel().addArmyIcon("Army3");
                    break;
                case 3:
                    positiveArmyPoints += 5;
                    player.setPositiveArmyPoints(positiveArmyPoints);
                    player.getPlayerPanel().getWonderPanel().getArmyPointsPanel().addArmyIcon("Army5");
                    break;
            }
        } else if (player.getNumOfShields() < opponent.getNumOfShields()) {
            int negativeArmyPoints = player.getNegativeArmyPoints() + 1;
            player.setNegativeArmyPoints(negativeArmyPoints);
            player.getPlayerPanel().getWonderPanel().getArmyPointsPanel().addArmyIcon("Army-1");
        }
    }

    public ArrayList<Card> getCardsInGrave() {
        return cardsInGrave;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
