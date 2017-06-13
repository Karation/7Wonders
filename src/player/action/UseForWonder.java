package player.action;

import cards.Card;
import effects.*;
import gui.playerPanel.wonderPanel.sidePanels.insidePanels.WonderBuildPanel;
import player.Player;
import wonders.WonderStage;

import java.util.ArrayList;

public class UseForWonder implements Action{
    private Card card;
   // private Wonders wonder;

    public UseForWonder(Card card) {
        this.card=card;
    }

    @Override
    public void executeAction( Player player) {

        if(player.getWonder().checkWhichWonderStageBuild()!=null){
            WonderStage wonderStage = player.getWonder().checkWhichWonderStageBuild();
            ArrayList<Effect> wonderStageEffects = wonderStage.getEffects();
            WonderBuildPanel wonderBuildPanel = player.getPlayerPanel().getWonderPanel().getMainWonderPanel().getWonderBuildPanel();
            for (Effect effect : wonderStageEffects) {
                String effectType = effect.getEffectType();
                switch (effectType) {
                    case "Culture":
                        addCultureEffect(player, wonderStage, (CultureEffect) effect);
                        break;
                    case "Army":
                        addArmyEffect(player, wonderStage, (ArmyEffect) effect, effectType);
                        break;
                    case "Trade":
                        addTradeEffect(player, wonderStage, (TradeEffect) effect, effectType);

                        break;
                    case "WonderEffect":
                        addWonderEffect(player, wonderStage, (WonderEffect) effect, effectType);

                        break;
                    case "Money":
                        addMoney(player, wonderStage, (MoneyEffect) effect);
                        break;
                }

                player.getCardsInHand().remove(card);
            }
            wonderBuildPanel.addBuiltLabel(wonderStage.getStage());
            ArrayList<WonderStage> wonderStages=player.getWonderStages();
            wonderStages.add(wonderStage);
            player.setWonderStages(wonderStages);
        }
    }

    private void addMoney(Player player, WonderStage wonderStage, MoneyEffect effect) {
        int money = player.getMoney() + effect.getAmount();
        player.setMoney(money);
        player.getPlayerPanel().getWonderPanel().getBuildingsPanel().updateMoneyIcon(money);
        wonderStage.setBuilt();
    }

    private void addCultureEffect(Player player, WonderStage wonderStage, CultureEffect effect) {
        int wonderPoints = player.getBuiltWonderPoints();
        wonderPoints+= effect.getNumOfPoints();
        player.setBuiltWonderPoints(wonderPoints);
        wonderStage.setBuilt();
    }

    private void addWonderEffect(Player player, WonderStage wonderStage, WonderEffect effect, String effectType) {
        player.addEffect(effectType, effect);
        wonderStage.setBuilt();
    }

    private void addTradeEffect(Player player, WonderStage wonderStage, TradeEffect effect, String effectType) {
        player.addEffect(effectType, effect);
        wonderStage.setBuilt();
    }

    private void addArmyEffect(Player player, WonderStage wonderStage, ArmyEffect effect, String effectType) {
        player.addEffect(effectType, effect);
        wonderStage.setBuilt();
    }
}
