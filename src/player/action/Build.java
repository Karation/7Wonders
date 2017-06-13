package player.action;

import cards.*;
import effects.*;
import gui.playerPanel.wonderPanel.*;
import gui.playerPanel.wonderPanel.sidePanels.BuildingsPanel;
import gui.playerPanel.wonderPanel.sidePanels.ResourcesPanel;
import gui.playerPanel.wonderPanel.sidePanels.SciencePanel;
import gui.playerPanel.wonderPanel.sidePanels.insidePanels.GuildPanel;
import gui.playerPanel.wonderPanel.sidePanels.insidePanels.TradePanel;
import player.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Build implements Action {
    private Card card;
    private boolean usedBuildForFreeEffect;

    public Build(Card card, boolean usedBuildForFreeEffect) {
        this.card = card;
        this.usedBuildForFreeEffect = usedBuildForFreeEffect;
    }

    @Override
    public void executeAction(Player player) {
        ArrayList<Card> cardsInHand = new ArrayList<>();
        if (player.getCardsInHand().size() != 0) {
            cardsInHand = player.getCardsInHand();
        }
        String type = card.getType();
        WonderPanel wonderPanel = player.getPlayerPanel().getWonderPanel();
        ResourcesPanel resourcesPanel = wonderPanel.getResourcesPanel();
        BuildingsPanel buildingsPanel = wonderPanel.getBuildingsPanel();
        TradePanel tradePanel = wonderPanel.getMainWonderPanel().getTradePanel();
        SciencePanel sciencePanel = wonderPanel.getSciencePanel();
        GuildPanel guildPanel = wonderPanel.getMainWonderPanel().getGuildPanel();
        JLabel backgroundPanel = player.getBackgroundPanel();
        int money = player.getMoney();


        switch (type) {
            case "Resource":
                String resourceName;
                Resource resource = (Resource) card;
                if (resource.getResourceEffect() instanceof MixedResourceEffect) {
                    MixedResourceEffect mixedResourceEffect = (MixedResourceEffect) resource.getResourceEffect();
                    resourceName = mixedResourceEffect.getEffectName();
                    player.addEffect(type, mixedResourceEffect);
                    int amount = Integer.parseInt(resource.getPrice());
                    money -= amount;
                    buildingsPanel.updateMoneyIcon(money);
                    player.setMoney(money);
                } else if (resource.getResourceEffect() instanceof DoubleResourceEffect) {
                    DoubleResourceEffect doubleResourceEffect = (DoubleResourceEffect) resource.getResourceEffect();
                    resourceName = doubleResourceEffect.getEffectName();
                    player.addEffect(type, doubleResourceEffect);
                    int amount = Integer.parseInt(resource.getPrice());
                    money -= amount;
                    buildingsPanel.updateMoneyIcon(money);
                    player.setMoney(money);
                } else {
                    ResourceEffect resourceEffect = (ResourceEffect) resource.getResourceEffect();
                    resourceName = resourceEffect.getResourceType();
                    player.addEffect(type, resourceEffect);
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

                player.addEffect(type, goodEffect);
                break;
            case "Army":
                Army army = (Army) card;
                ArmyEffect armyEffect = (ArmyEffect) army.getArmyEffect();
                int numOfShields = armyEffect.getNumOfShields();
                buildingsPanel.addArmyPoints(numOfShields, army);

                player.addEffect(type, armyEffect);

                if (!Objects.equals(army.getCanBuild(), "null")) {
                    player.getCanBuildArray().add(army.getCanBuild());
                    player.getPlayerPanel().getCanBuildPanel().addToList(army.getCanBuild());
                }
                break;
            case "Culture":
                Culture culture = (Culture) card;
                CultureEffect cultureEffect = (CultureEffect) culture.getCultureEffect();
                int numOfPoints = cultureEffect.getNumOfPoints();
                buildingsPanel.addCulturePoints(numOfPoints, culture);

                player.addEffect(type, cultureEffect);

                if (culture.getCanBuild() != null) {
                    player.getCanBuildArray().add(culture.getCanBuild());
                    player.getPlayerPanel().getCanBuildPanel().addToList(culture.getCanBuild());
                }
                break;
            case "Science":
                Science science = (Science) card;
                ScienceEffect scienceEffect = (ScienceEffect) science.getScienceEffect();
                String symbol = scienceEffect.getScienceSymbol();
                sciencePanel.addScienceIcon(symbol, science);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());

                player.addEffect(type, scienceEffect);

                if (science.getCanBuild() != null && science.getCanBuild2() != null) {
                    player.getCanBuildArray().add(science.getCanBuild());
                    player.getCanBuildArray().add(science.getCanBuild2());
                    player.getPlayerPanel().getCanBuildPanel().addToList(science.getCanBuild());
                    player.getPlayerPanel().getCanBuildPanel().addToList(science.getCanBuild2());
                }
                break;
            case "Trade":
                Trade trade = (Trade) card;
                TradeEffect tradeEffect = (TradeEffect) trade.getTradeEffect();
                String emblem = tradeEffect.getEmblem();
                tradePanel.addTradeIcon(emblem, trade);
                backgroundPanel.paintAll(backgroundPanel.getGraphics());

                money += tradeEffect.resolveInstantTradeEffect(player, tradeEffect.getEmblem());
                player.setMoney(money);

                if (trade.getCanBuild() != null) {
                    player.getCanBuildArray().add(trade.getCanBuild());
                    player.getPlayerPanel().getCanBuildPanel().addToList(trade.getCanBuild());
                }
                player.addEffect(type, tradeEffect);
                break;
            case "Guild":
                Guild guild = (Guild) card;
                GuildEffect guildEffect = (GuildEffect) guild.getGuildEffect();
                String guildEffectType = guildEffect.getGuildEffectType();
                guildPanel.addIcon(guildEffectType, guild);

                player.addEffect(type, guildEffect);
        }
        if (player.getCardsInHand().size() != 0) {
            player.setCardToBeRemoved(card);
            cardsInHand.remove(card);
        } else {
            player.getBoard().getCardsInGrave().remove(card);
        }

        if (usedBuildForFreeEffect) {
            player.setUsedBuildForFreeEffect();
        }
    }
}
