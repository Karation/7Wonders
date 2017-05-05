package player.action;

import cards.Card;
import cards.Trade;
import effects.*;
import gui.playerPanel.WonderBuildPanel;
import player.Player;
import wonders.WonderStage;
import wonders.Wonders;

import java.util.ArrayList;

public class UseForWonder implements Action{
    private Card card;
    private Wonders wonder;

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
                        CultureEffect cultureEffect = (CultureEffect) effect;
                        int wonderPoints = player.getBuiltWonderPoints();
                        wonderPoints+=cultureEffect.getNumOfPoints();
                        player.setBuiltWonderPoints(wonderPoints);
                        wonderStage.setBuilt(true);
                        break;
                    case "Army":
                        ArmyEffect armyEffect = (ArmyEffect) effect;
                        player.addEffect(effectType, armyEffect);
                        wonderStage.setBuilt(true);
                        break;
                    case "Trade":
                        TradeEffect tradeEffect = (TradeEffect) effect;
                        player.addEffect(effectType, tradeEffect);
                        wonderStage.setBuilt(true);

                        break;
                    case "WonderEffect":
                        WonderEffect wonderEffect = (WonderEffect) effect;
                        player.addEffect(effectType, wonderEffect);
                        wonderStage.setBuilt(true);

                        break;
                    case "Money":
                        MoneyEffect moneyEffect = (MoneyEffect) effect;
                        int money = player.getMoney() + moneyEffect.getAmount();
                        player.setMoney(money);
                        player.getPlayerPanel().getWonderPanel().getBuildingsPanel().updateMoneyIcon(money);
                        wonderStage.setBuilt(true);
                        break;
                }

                player.getCardsInHand().remove(card);
            }
            wonderBuildPanel.addBuiltLabel();
            ArrayList<WonderStage> wonderStages=player.getWonderStages();
            wonderStages.add(wonderStage);
            player.setWonderStages(wonderStages);
        }
    }
}
