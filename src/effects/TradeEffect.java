package effects;

import gui.oponentsPanel.LeftOponentPanel;
import gui.oponentsPanel.RightOponentPanel;
import player.Player;

import javax.swing.*;


public class TradeEffect extends Effect {
    private String emblem;

    TradeEffect(String effectType, String emblem) {
        super(effectType);
        this.emblem = emblem;
    }

    public String getEmblem() {
        return emblem;
    }

    public int resolveTradeEffect(TradeEffect tradeEffect, Effect resourceOrGood, JPanel opponentPanel) {
        int moneyToPay;
        String emblemName = tradeEffect.getEmblem();

        switch (emblemName) {
            case "WestTrade":
                if ((resourceOrGood instanceof ResourceEffect || resourceOrGood instanceof MixedResourceEffect || resourceOrGood instanceof DoubleResourceEffect) && opponentPanel instanceof LeftOponentPanel) {
                    moneyToPay = 1;
                    return moneyToPay;
                } else {
                    moneyToPay = 2;
                    return moneyToPay;
                }
            case "EastTrade":
                if ((resourceOrGood instanceof ResourceEffect || resourceOrGood instanceof MixedResourceEffect || resourceOrGood instanceof DoubleResourceEffect) && opponentPanel instanceof RightOponentPanel) {
                    moneyToPay = 1;
                    return moneyToPay;
                } else {
                    moneyToPay = 2;
                    return moneyToPay;
                }
            case "ResourceTrade":
                if ((resourceOrGood instanceof ResourceEffect || resourceOrGood instanceof MixedResourceEffect || resourceOrGood instanceof DoubleResourceEffect) && (opponentPanel instanceof RightOponentPanel || opponentPanel instanceof LeftOponentPanel)) {
                    moneyToPay = 1;
                    return moneyToPay;
                }
            case "GoodsTrade":
                if (resourceOrGood instanceof GoodEffect) {
                    moneyToPay = 1;
                    return moneyToPay;
                } else {
                    moneyToPay = 2;
                    return moneyToPay;
                }
        }
        moneyToPay = 2;
        return moneyToPay;
    }

    public int resolveInstantTradeEffect(Player player, String tradeEmblem) {
        Player leftOpponent = player.getLeftPlayer();
        Player rightOpponent = player.getRightPlayer();

        switch (tradeEmblem) {
            case "ResourceMoney":
                return leftOpponent.getResourceEffects().size() + rightOpponent.getResourceEffects().size() + player.getResourceEffects().size();
            case "WonderStagesMix":
                return (player.getWonderStages().size()) * 3;
            case "TradeCardsMix":
                return player.getTradeEffects().size();
            case "ResourceCardsMix":
                return player.getResourceEffects().size();
        }
        return 0;
    }

    public int resolveTradePointsEffect(Player player, String tradeEmblem){
        switch (tradeEmblem) {
            case "WonderStagesMix":
                return (player.getWonderStages().size());
            case "TradeCardsMix":
                return player.getTradeEffects().size();
            case "ResourceCardsMix":
                return player.getResourceEffects().size();
        }
        return 0;
    }

}
