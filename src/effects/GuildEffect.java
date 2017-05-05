package effects;

import player.Player;

/**
 * Created by mkrec_000 on 27/04/2017.
 */
public class GuildEffect extends Effect{
    String guildEffectType;
    public GuildEffect(String effectType, String guildEffectType) {
        super(effectType);
        this.guildEffectType=guildEffectType;
    }

    public int resolveGuildEffect(Player player){
        Player leftOpponent = player.getLeftPlayer();
        Player rightOpponent = player.getRightPlayer();
        int pointsToAdd=0;

        switch (this.getGuildEffectType()){
            case "WonderStages":

                break;
            case "GoodCards":
                pointsToAdd=leftOpponent.getGoodEffects().size();
                return pointsToAdd+=rightOpponent.getGoodEffects().size();

            case "CultureCards":
                pointsToAdd=leftOpponent.getCultureEffects().size();
                return pointsToAdd+=rightOpponent.getCultureEffects().size();

            case "TradeCards":
                pointsToAdd=leftOpponent.getTradeEffects().size();
                return pointsToAdd+=rightOpponent.getTradeEffects().size();

            case "ScienceCards":
                pointsToAdd=leftOpponent.getScienceEffects().size();
                return pointsToAdd+=rightOpponent.getScienceEffects().size();

            case "ArmyCards":
                pointsToAdd=leftOpponent.getArmyEffects().size();
                return pointsToAdd+=rightOpponent.getScienceEffects().size();

            case "ResourceCards":
                pointsToAdd=leftOpponent.getResourceEffects().size();
                return pointsToAdd+=rightOpponent.getResourceEffects().size();

            case "Defeats":
                pointsToAdd=leftOpponent.getNegativeArmyPoints();
                return pointsToAdd+=rightOpponent.getNegativeArmyPoints();

            case "ResourceGoodGuildCards":
                return pointsToAdd=player.getResourceEffects().size() + leftOpponent.getGoodEffects().size() + leftOpponent.getGuildEffects().size();

            case "ChooseSymbol":

                break;
        }
        return 0;
    }
    public String getGuildEffectType() {
        return guildEffectType;
    }

    public void setGuildEffectType(String guildEffectType) {
        this.guildEffectType = guildEffectType;
    }
}
