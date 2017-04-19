package player;

import board.Board;
import cards.*;
import effects.*;
import gui.oponentsPanel.OpponentPanel;
import gui.playerPanel.*;
import wonders.WonderStage;
import wonders.Wonders;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Player {
    public final static int MAX_CARDS_IN_HAND = 7;
    private final static int STARTING_MONEY = 3;
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Card> copiedCardsInHand = new ArrayList<>();
    private ArrayList<Effect> copiedResourceEffects = new ArrayList<>();
    private ArrayList<Effect> copiedGoodEffects = new ArrayList<>();
    private ArrayList<Effect> copiedBoughtResourceEffects = new ArrayList<>();
    private ArrayList<Effect> copiedBoughtGoodEffects = new ArrayList<>();
    private ArrayList<Effect> copiedTradeEffects = new ArrayList<>();

    private Wonders wonder;
    private int money = STARTING_MONEY;
    private int moneyToBeTransfered = 0;
    private int numOfShields = 0;
    private int armyPositivePoints = 0;
    private int armyNegativePoints = 0;
    private ArrayList<ArrayList<Effect>> effects = addToEffectArray();
    private ArrayList<Effect> resourceEffects = new ArrayList<>();
    private ArrayList<Effect> cultureEffects = new ArrayList<>();
    private ArrayList<Effect> armyEffects = new ArrayList<>();
    private ArrayList<Effect> scienceEffects = new ArrayList<>();
    private ArrayList<Effect> goodEffects = new ArrayList<>();
    private ArrayList<Effect> tradeEffects = new ArrayList<>();
    private ArrayList<Effect> wonderEffects = new ArrayList<>();
    private ArrayList<String> canBuild = new ArrayList<>();
    private ArrayList<Effect> boughtResourceEffects = new ArrayList<>();
    private ArrayList<Effect> boughtGoodEffects = new ArrayList<>();
    private Card cardToBeRemoved;

    private PlayerPanel playerPanel;
    private Player leftPlayer;
    private Player rightPlayer;
    private JLabel backgroundPanel;
    private player.action.Action action;
    private Board board;
    private GridBagConstraints constraints;
    private OpponentPanel opponentPanel;

    private boolean usedBuildForFreeEffect = false;


    public Player() {
    }

    public PlayerPanel start(JLabel backgroundPanel, GridBagConstraints constraints, ArrayList<Card> cards, Wonders wonder) throws IOException {
        playerPanel = new PlayerPanel(cards, this, wonder);
        this.setConstraints(constraints);
        backgroundPanel.add(playerPanel, constraints);
        this.setBackgroundPanel(backgroundPanel);
        return playerPanel;
    }

    public void nextTurn() throws IOException {
        CardsPanel cardsPanel;
        playerPanel.remove(playerPanel.getCardsPanel());
        Player rightPlayer = this.getRightPlayer();
        this.setCardsInHand(rightPlayer.getCopiedCardsInHand());
        cardsPanel = new CardsPanel(cardsInHand, this);
        constraints.gridy = 1;
        playerPanel.setCardsPanel(cardsPanel);
        playerPanel.add(cardsPanel, constraints);
        backgroundPanel.paintAll(backgroundPanel.getGraphics());

    }
    public void nextAge() throws IOException{
        CardsPanel cardsPanel;
        playerPanel.remove(playerPanel.getCardsPanel());
        cardsPanel = new CardsPanel(this.getCardsInHand(), this);
        constraints.gridy = 1;
        playerPanel.setCardsPanel(cardsPanel);
        playerPanel.add(cardsPanel, constraints);
        backgroundPanel.paintAll(backgroundPanel.getGraphics());
    }


    public void setWonder(Wonders wonder) {
        this.wonder = wonder;
        //add production from wonder.
        Effect effect = wonder.getProduction();
        this.addEffect(effect.getEffectType(), effect);
    }


    private ArrayList<ArrayList<Effect>> addToEffectArray() {
        ArrayList<ArrayList<Effect>> effects = new ArrayList<>();
        effects.add(resourceEffects);
        effects.add(armyEffects);
        effects.add(cultureEffects);
        effects.add(goodEffects);
        effects.add(scienceEffects);
        effects.add(tradeEffects);
        effects.add(wonderEffects);

        return effects;
    }

    public void addEffect(String type, Effect effect) {
        switch (type) {
            case "Resource":
                resourceEffects.add(effect);
                break;
            case "Good":
                goodEffects.add(effect);
                break;
            case "Army":
                armyEffects.add(effect);
                break;
            case "Culture":
                cultureEffects.add(effect);
                break;
            case "Science":
                scienceEffects.add(effect);
                break;
            case "Trade":
                tradeEffects.add(effect);
                break;
            case "WonderEffect":
                wonderEffects.add(effect);
                break;
        }
    }


    public void startAction() {
        action.executeAction(this);
    }


    public void clearBoughtGoodAndResourceEffects() {
        boughtResourceEffects.clear();
        boughtGoodEffects.clear();

    }

    private void clearAllCopiedEffectArrays() {
        copiedBoughtResourceEffects=new ArrayList<>();
        copiedBoughtGoodEffects=new ArrayList<>();
        copiedGoodEffects=new ArrayList<>();
        copiedResourceEffects=new ArrayList<>();

        copiedTradeEffects = new ArrayList<>();
    }


    public Player getLeftPlayerFromArray(ArrayList<Player> players, int i) {

        if (i == 0) {
            leftPlayer = players.get(players.size() - 1);
        } else {
            leftPlayer = players.get(i - 1);
        }
        return leftPlayer;
    }

    public Player getRightPlayerFromArray(ArrayList<Player> players, int i) {

        if (i == players.size() - 1) {
            rightPlayer = players.get(0);
        } else {
            rightPlayer = players.get(i + 1);
        }
        return rightPlayer;
    }

    private void refreshCopiedGoodsAndResources() {

        copiedResourceEffects.addAll(resourceEffects);
        copiedGoodEffects.addAll(goodEffects);
        copiedBoughtGoodEffects.addAll(boughtGoodEffects);
        copiedBoughtResourceEffects.addAll(boughtResourceEffects);

        copiedTradeEffects.addAll(tradeEffects);

    }

    private void refreshGoodsResourcesAndTradeEffects() {
        resourceEffects=new ArrayList<>();
        goodEffects=new ArrayList<>();
        boughtGoodEffects=new ArrayList<>();
        boughtResourceEffects=new ArrayList<>();
        tradeEffects = new ArrayList<>();
        resourceEffects.addAll(copiedResourceEffects);
        goodEffects.addAll(copiedGoodEffects);
        boughtResourceEffects.addAll(copiedBoughtResourceEffects);
        boughtGoodEffects.addAll(copiedBoughtGoodEffects);

        tradeEffects.addAll(copiedTradeEffects);

        for (Effect resourceEffect : resourceEffects) {
            if (resourceEffect instanceof DoubleResourceEffect) {
                ((DoubleResourceEffect) resourceEffect).setNumberOfResources(2);
            }
        }
    }

    public ArrayList<Boolean> checkIfCanBuild(Card card, WonderStage wonderStage) {
        char[] price;
        if (wonderStage == null) {
            price = card.getPrice().toCharArray();
        } else {
            price = wonderStage.getPrice().toCharArray();
        }
        ArrayList<Boolean> found = new ArrayList<>();
        refreshCopiedGoodsAndResources();

        for (int i = 0; i < price.length; i++) {

            char temp = price[i];
            if (temp == 'P') {//Paper
                found.add(findInGoodEffectsArray("Paper"));
            } else if (temp == 'C') {//Cloth
                found.add(findInGoodEffectsArray("Cloth"));
            } else if (temp == 'G') {//Glass
                found.add(findInGoodEffectsArray("Glass"));
            } else if (temp == 'O') {//Ore
                found.add(findInResourceEffectsArray("Ore"));
            } else if (temp == 'W') {//Wood
                found.add(findInResourceEffectsArray("Wood"));
            } else if (temp == 'S') {//Stone
                found.add(findInResourceEffectsArray("Stone"));
            } else if (temp == 'Z') {//Clay
                found.add(findInResourceEffectsArray("Clay"));
            } else if (temp == '1') {//1 Money
                found.add(checkIfEnoughMoney("1"));
            }
        }
        refreshGoodsResourcesAndTradeEffects();
        clearAllCopiedEffectArrays();
        return found;
    }

    private Boolean checkIfEnoughMoney(String s) {
        int money = this.getMoney();
        return money >= 1;
    }

    private boolean findInResourceEffectsArray(String effect) {

        for (int i = 0; i < resourceEffects.size(); i++) {
            if (resourceEffects.get(i) instanceof ResourceEffect) {
                ResourceEffect resource = (ResourceEffect) resourceEffects.get(i);
                if (resource.getResourceType().equals(effect)) {
                    resourceEffects.remove(i);
                    return true;
                }
                //dodane
            }else if(resourceEffects.get(i) instanceof DoubleResourceEffect){
                DoubleResourceEffect doubleResource = (DoubleResourceEffect) resourceEffects.get(i);
                if (doubleResource.getResourceType().equals(effect)){
                    if(doubleResource.getNumberOfResources()==2){
                        doubleResource.setNumberOfResources(1);
                        return true;
                    }else{
                        resourceEffects.remove(i);
                        return true;
                    }
                }
            } else if (resourceEffects.get(i) instanceof MixedResourceEffect) {
                MixedResourceEffect mixedResource = (MixedResourceEffect) resourceEffects.get(i);
                if (mixedResource.getResourceType1().equals(effect) || mixedResource.getResourceType2().equals(effect)) {
                    resourceEffects.remove(i);
                    return true;
                }
            }
        }

        for (int i = 0; i < boughtResourceEffects.size(); i++) {
            if (boughtResourceEffects.get(i) instanceof ResourceEffect) {
                ResourceEffect resourceEffect = (ResourceEffect) boughtResourceEffects.get(i);
                if (resourceEffect.getResourceType().equals(effect)) {
                    boughtResourceEffects.remove(i);
                    return true;
                }
            } else if (boughtResourceEffects.get(i) instanceof DoubleResourceEffect){
                DoubleResourceEffect doubleResourceEffect = (DoubleResourceEffect) boughtResourceEffects.get(i);
                if (doubleResourceEffect.getResourceType().equals(effect)){
                    boughtResourceEffects.remove(i);
                    return true;
                }
            }
            else if (boughtResourceEffects.get(i) instanceof MixedResourceEffect) {
                MixedResourceEffect mixedResource = (MixedResourceEffect) boughtResourceEffects.get(i);
                if (mixedResource.getResourceType1().equals(effect) || mixedResource.getResourceType2().equals(effect)) {
                    boughtResourceEffects.remove(i);
                    return true;
                }
            }
        }
        for (int i = 0; i < tradeEffects.size(); i++) {
            TradeEffect tradeEffect = (TradeEffect) tradeEffects.get(i);
            if (tradeEffect.getEmblem().equals("ResourceProduction")){
                tradeEffects.remove(tradeEffect);
                return true;
            }
        }

        return false;
    }

    private boolean findInGoodEffectsArray(String effect) {
        for (int i = 0; i < goodEffects.size(); i++) {
            GoodEffect good = (GoodEffect) goodEffects.get(i);
            if (good.getGoodType().equals(effect)) {
                goodEffects.remove(i);
                return true;
            }
        }
        for (int i = 0; i < boughtGoodEffects.size(); i++) {
            GoodEffect good = (GoodEffect) boughtGoodEffects.get(i);
            if (good.getGoodType().equals(effect)) {
                boughtGoodEffects.remove(i);
                return true;
            }
        }
        for (int i = 0; i < tradeEffects.size(); i++) {
            TradeEffect good = (TradeEffect) tradeEffects.get(i);
            if (good.getEmblem().equals("GoodProduction")){
                tradeEffects.remove(i);
                return true;
            }
        }
        return false;
    }

    public void payOpponentForBoughtItem(int moneyToPay) {
        moneyToBeTransfered += moneyToPay;
    }

    public int getMoneyToBeTransfered() {
        return moneyToBeTransfered;
    }

    public void setMoneyToBeTransfered(int moneyToBeTransfered) {
        this.moneyToBeTransfered = moneyToBeTransfered;
    }

    public void setBackgroundPanel(JLabel backgroundPanel) {
        this.backgroundPanel = backgroundPanel;
    }

    public JLabel getBackgroundPanel() {
        return backgroundPanel;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(GridBagConstraints constraints) {
        this.constraints = constraints;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public void setLeftPlayer(Player leftPlayer) {
        this.leftPlayer = leftPlayer;
    }

    public void setRightPlayer(Player rightPlayer) {
        this.rightPlayer = rightPlayer;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public Wonders getWonder() {
        return wonder;
    }

    public OpponentPanel getOpponentPanel() {
        return opponentPanel;
    }

    public void setOpponentPanel(OpponentPanel opponentPanel) {
        this.opponentPanel = opponentPanel;
    }

    public ArrayList<ArrayList<Effect>> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<ArrayList<Effect>> effects) {
        this.effects = effects;
    }

    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    public void setAction(player.action.Action action) {
        this.action = action;
    }

    public player.action.Action getAction() {
        return action;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCopiedCardsInHand(ArrayList<Card> copiedCardsInHand) {
        this.copiedCardsInHand = copiedCardsInHand;
    }

    public ArrayList<Card> getCopiedCardsInHand() {
        return copiedCardsInHand;
    }

    public ArrayList<Effect> getCopiedResourceEffects() {
        return copiedResourceEffects;
    }

    public ArrayList<Effect> getCopiedGoodEffects() {
        return copiedGoodEffects;
    }

    public void setCopiedResourceEffects(ArrayList<Effect> copiedResourceEffects) {
        this.copiedResourceEffects = copiedResourceEffects;
    }

    public void setCopiedGoodEffects(ArrayList<Effect> copiedGoodEffects) {
        this.copiedGoodEffects = copiedGoodEffects;
    }

    public ArrayList<Effect> getCopiedBoughtResourceEffects() {
        return copiedBoughtResourceEffects;
    }

    public void setCopiedBoughtResourceEffects(ArrayList<Effect> copiedBoughtResourceEffects) {
        this.copiedBoughtResourceEffects = copiedBoughtResourceEffects;
    }

    public ArrayList<Effect> getCopiedBoughtGoodEffects() {
        return copiedBoughtGoodEffects;
    }

    public void setCopiedBoughtGoodEffects(ArrayList<Effect> copiedBoughtGoodEffects) {
        this.copiedBoughtGoodEffects = copiedBoughtGoodEffects;
    }

    public ArrayList<Effect> getBoughtResourceEffects() {
        return boughtResourceEffects;
    }

    public ArrayList<Effect> getBoughtGoodEffects() {
        return boughtGoodEffects;
    }

    public void setBoughtResourceEffects(ArrayList<Effect> boughtResourceEffects) {
        this.boughtResourceEffects = boughtResourceEffects;
    }

    public void setBoughtGoodEffects(ArrayList<Effect> boughtGoodEffects) {
        this.boughtGoodEffects = boughtGoodEffects;
    }

    public void setResourceEffects(ArrayList<Effect> resourceEffects) {
        this.resourceEffects = resourceEffects;
    }

    public void setGoodEffects(ArrayList<Effect> goodEffects) {
        this.goodEffects = goodEffects;
    }

    public ArrayList<Effect> getArmyEffects() {
        return armyEffects;
    }

    public void setArmyEffects(ArrayList<Effect> armyEffects) {
        this.armyEffects = armyEffects;
    }

    public ArrayList<Effect> getResourceEffects() {
        return resourceEffects;
    }

    public ArrayList<Effect> getGoodEffects() {
        return goodEffects;
    }

    public ArrayList<Effect> getTradeEffects() {
        return tradeEffects;
    }

    public Card getCardToBeRemoved() {
        return cardToBeRemoved;
    }

    public void setCardToBeRemoved(Card cardToBeRemoved) {
        this.cardToBeRemoved = cardToBeRemoved;
    }

    public int getNumOfShields() {
        return numOfShields;
    }

    public void setNumOfShields(int numOfShields) {
        this.numOfShields = numOfShields;
    }

    public int getPositiveArmyPoints() {
        return armyPositivePoints;
    }

    public void setPositiveArmyPoints(int armyPositivePoints) {
        this.armyPositivePoints = armyPositivePoints;
    }

    public int getNegativeArmyPoints() {
        return armyNegativePoints;
    }

    public void setNegativeArmyPoints(int armyNegativePoints) {
        this.armyNegativePoints = armyNegativePoints;
    }

    public ArrayList<Effect> getWonderEffects() {
        return wonderEffects;
    }

    public void setWonderEffects(ArrayList<Effect> wonderEffects) {
        this.wonderEffects = wonderEffects;
    }

    public boolean isUsedBuildForFreeEffect() {
        return usedBuildForFreeEffect;
    }

    public void setUsedBuildForFreeEffect(boolean usedBuildForFreeEffect) {
        this.usedBuildForFreeEffect = usedBuildForFreeEffect;
    }

    public ArrayList<String> getCanBuildArray() {
        return canBuild;
    }

    public void setCanBuildArray(ArrayList<String> canBuild) {
        this.canBuild = canBuild;
    }
}