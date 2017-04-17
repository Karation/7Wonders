package effects;

import cards.Good;
import cards.Trade;
import gui.oponentsPanel.LeftOponentPanel;
import gui.oponentsPanel.RightOponentPanel;

import javax.swing.*;

/**
 * Created by mkrec_000 on 28/03/2017.
 */
public class TradeEffect extends Effect{
    String emblem;

    public TradeEffect(String effectType, String emblem) {
        super(effectType);
        this.emblem = emblem;
    }

    public String getEmblem() {
        return emblem;
    }

    public int resolveTradeEffect(TradeEffect tradeEffect, Effect resourceOrGood, JPanel opponentPanel){
        int moneyToPay;
        String emblemName = tradeEffect.getEmblem();

        switch (emblemName){
            case "WestTrade":
                if ((resourceOrGood instanceof ResourceEffect ||resourceOrGood instanceof MixedResourceEffect) && opponentPanel instanceof LeftOponentPanel){
                    moneyToPay=1;
                    return moneyToPay;
                }else {
                    moneyToPay = 2;
                    return moneyToPay;
                }
            case "EastTrade":
                if ((resourceOrGood instanceof ResourceEffect || resourceOrGood instanceof MixedResourceEffect) && opponentPanel instanceof RightOponentPanel){
                    moneyToPay=1;
                    return moneyToPay;
                }else{
                    moneyToPay=2;
                    return moneyToPay;
                }
            case "ResourceTrade":
                if((resourceOrGood instanceof ResourceEffect || resourceOrGood instanceof MixedResourceEffect) && (opponentPanel instanceof RightOponentPanel || opponentPanel instanceof LeftOponentPanel)){
                    moneyToPay=1;
                    return moneyToPay;
                }
            case "GoodsTrade":
                if (resourceOrGood instanceof GoodEffect ){
                    moneyToPay=1;
                    return moneyToPay;
                }else{
                    moneyToPay=2;
                    return moneyToPay;
                }
        }
        moneyToPay=2;
        return moneyToPay;
    }
}
