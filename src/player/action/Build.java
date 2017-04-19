package player.action;

import cards.*;
import effects.*;
import gui.playerPanel.*;
import player.Player;

import javax.swing.*;
import java.util.ArrayList;

public class Build  implements Action{
    Card card;
    boolean usedBuildForFreeEffect;

    public Build(Card card, boolean usedBuildForFreeEffect) {
        this.card=card;
        this.usedBuildForFreeEffect = usedBuildForFreeEffect;
    }

    @Override
    public void executeAction(Player player) {
        ArrayList<Card> cardsInHand=new ArrayList<>();
        if (player.getCardsInHand().size()!=0) {
            cardsInHand = player.getCardsInHand();
        }
        String type = card.getType();
        WonderPanel wonderPanel =player.getPlayerPanel().getWonderPanel();
        ResourcesPanel resourcesPanel = wonderPanel.getResourcesPanel();
        BuildingsPanel buildingsPanel = wonderPanel.getBuildingsPanel();
        TradePanel tradePanel=wonderPanel.getMainWonderPanel().getTradePanel();
        JLabel backgroundPanel = player.getBackgroundPanel();
        int money = player.getMoney();


        switch (type){
            case "Resource":
                String resourceName;
                Resource resource = (Resource) card;
                if(resource.getResourceEffect() instanceof MixedResourceEffect) {
                    MixedResourceEffect mixedResourceEffect=(MixedResourceEffect) resource.getResourceEffect();
                    resourceName=mixedResourceEffect.getEffectName();
                    player.addEffect(type, mixedResourceEffect);
                    int amount=Integer.parseInt(resource.getPrice());
                    money-=amount;
                    buildingsPanel.updateMoneyIcon(money);
                    player.setMoney(money);
                }else if (resource.getResourceEffect()instanceof DoubleResourceEffect){
                    DoubleResourceEffect doubleResourceEffect = (DoubleResourceEffect) resource.getResourceEffect();
                    resourceName = doubleResourceEffect.getEffectName();
                    player.addEffect(type, doubleResourceEffect);
                    int amount=Integer.parseInt(resource.getPrice());
                    money-=amount;
                    buildingsPanel.updateMoneyIcon(money);
                    player.setMoney(money);
                }
                else{
                    ResourceEffect resourceEffect  = (ResourceEffect) resource.getResourceEffect();
                    resourceName=resourceEffect.getResourceType();
                    player.addEffect(type, resourceEffect);
                }
                resourcesPanel.addResourceIcon(resourceName, resource);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());
                break;
            case "Good":
                Good good =(Good) card;
                GoodEffect goodEffect = (GoodEffect) good.getGoodEffect();
                String goodName = goodEffect.getGoodType();
                resourcesPanel.addGoodsIcon(goodName, good);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());

                player.addEffect(type, goodEffect);
                break;
            case "Army":
                Army army =(Army) card;
                ArmyEffect armyEffect=(ArmyEffect) army.getArmyEffect();
                int numOfShields = armyEffect.getNumOfShields();
                buildingsPanel.addArmyPoints(numOfShields, army);

                player.addEffect(type, armyEffect);

                if (army.getCanBuild()!=null){
                    player.getCanBuildArray().add(army.getCanBuild());
                }
                break;
            case "Culture":
                Culture culture = (Culture) card;
                CultureEffect cultureEffect = (CultureEffect) culture.getCultureEffect();
                int numOfPoints = cultureEffect.getNumOfPoints();
                buildingsPanel.addCulturePoints(numOfPoints, culture);

                player.addEffect(type, cultureEffect);

                if (culture.getCanBuild()!=null){
                    player.getCanBuildArray().add(culture.getCanBuild());
                }
                break;
            case "Science":
                Science science = (Science) card;
                ScienceEffect scienceEffect = (ScienceEffect) science.getScienceEffect();
                String symbol = scienceEffect.getScienceSymbol();
                buildingsPanel.addScienceIcon(symbol, science);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());

                player.addEffect(type, scienceEffect);

                if (science.getCanBuild()!=null && science.getCanBuild2()!=null){
                    player.getCanBuildArray().add(science.getCanBuild());
                    player.getCanBuildArray().add(science.getCanBuild2());
                }
                break;
            case "Trade":
                Trade trade = (Trade) card;
                TradeEffect tradeEffect=(TradeEffect) trade.getTradeEffect();
                String emblem = tradeEffect.getEmblem();
                tradePanel.addTradeIcon(emblem, trade);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());

                //poprawic
                if (tradeEffect.getEmblem().equals("ResourceMoney")){
                    money += tradeEffect.resolveMoneyResourceEffect(player);
                    player.setMoney(money);
                }
                player.addEffect(type, tradeEffect);

                if (trade.getCanBuild()!=null){
                    player.getCanBuildArray().add(trade.getCanBuild());
                }
                break;
        }
        if (player.getCardsInHand().size()!=0) {
            player.setCardToBeRemoved(card);
            cardsInHand.remove(card);
        }else{
            player.getBoard().getCardsInGrave().remove(card);
        }

        if (usedBuildForFreeEffect){
            player.setUsedBuildForFreeEffect(true);
        }
    }
}
