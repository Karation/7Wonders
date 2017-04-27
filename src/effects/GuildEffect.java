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

    public void resolveGuildEffect(Player player, String guildEffectType){
        Player leftOpponent = player.getLeftPlayer();
        Player rightOpponent = player.getRightPlayer();
        int pointsToAdd=0;

        switch (guildEffectType){
            case "WonderStages":

                break;
            case "GoodCards":
                pointsToAdd=leftOpponent.getGoodEffects().size();
                pointsToAdd+=rightOpponent.getGoodEffects().size();

                break;
            case "CultureCards":
                pointsToAdd=leftOpponent.getCultureEffects().size();
                pointsToAdd+=rightOpponent.getCultureEffects().size();

                break;
            case "TradeCards":
                pointsToAdd=leftOpponent.getTradeEffects().size();
                pointsToAdd+=rightOpponent.getTradeEffects().size();

                break;
            case "ScienceCards":
                pointsToAdd=leftOpponent.getScienceEffects().size();
                pointsToAdd+=rightOpponent.getScienceEffects().size();

                break;
            case "ArmyCards":
                pointsToAdd=leftOpponent.getArmyEffects().size();
                pointsToAdd+=rightOpponent.getScienceEffects().size();

                break;
            case "ResourceCards":
                pointsToAdd=leftOpponent.getResourceEffects().size();
                pointsToAdd+=rightOpponent.getResourceEffects().size();

                break;
            case "Defeats":
                pointsToAdd=leftOpponent.getNegativeArmyPoints();
                pointsToAdd+=rightOpponent.getNegativeArmyPoints();

                break;
            case "ResourceGoodGuildCards":
                pointsToAdd=player.getResourceEffects().size() + leftOpponent.getGoodEffects().size() + leftOpponent.getGuildEffects().size();

                break;
            case "ChooseSymbol":

                break;
        }
    }
    public String getGuildEffectType() {
        return guildEffectType;
    }

    public void setGuildEffectType(String guildEffectType) {
        this.guildEffectType = guildEffectType;
    }
}
