package player.action;

import cards.Card;
import gui.playerPanel.BuildingsPanel;
import gui.playerPanel.ResourcesPanel;
import gui.playerPanel.WonderPanel;
import player.Player;

import java.util.ArrayList;

/**
 * Created by mkrec_000 on 26/03/2017.
 */
public class Sell implements Action{
    int index;

    public Sell(int index) {
        this.index=index;
    }

    @Override
    public void executeAction(Player player) {
        int money=player.getMoney();
        money+=3;
        player.setMoney(money);
        ArrayList<Card> cardsInHand= player.getCardsInHand();
        WonderPanel wonderPanel =player.getPlayerPanel().getWonderPanel();
        BuildingsPanel buildingsPanel = wonderPanel.getBuildingsPanel();
        buildingsPanel.updateMoneyIcon(money);
        player.setCardToBeRemoved(cardsInHand.get(index));
        cardsInHand.remove(index);
        player.setCardsInHand(cardsInHand);
    }

    public int getIndex() {
        return index;
    }
}
