package effects;

import player.Player;

public class GuildEffect extends Effect{
    private String guildEffectType;
    public GuildEffect(String effectType, String guildEffectType) {
        super(effectType);
        this.guildEffectType=guildEffectType;
    }

    public int resolveGuildEffect(Player player){
        Player leftOpponent = player.getLeftPlayer();
        Player rightOpponent = player.getRightPlayer();
        int pointsToAdd;

        switch (this.getGuildEffectType()){
            case "WonderStages":

                break;
            case "GoodCards":
                pointsToAdd=leftOpponent.getGoodEffects().size() + rightOpponent.getGoodEffects().size();
                return pointsToAdd;

            case "CultureCards":
                pointsToAdd=leftOpponent.getCultureEffects().size() + rightOpponent.getCultureEffects().size();
                return pointsToAdd;

            case "TradeCards":
                pointsToAdd=leftOpponent.getTradeEffects().size() + rightOpponent.getTradeEffects().size();
                return pointsToAdd;

            case "ScienceCards":
                pointsToAdd=leftOpponent.getScienceEffects().size() + rightOpponent.getScienceEffects().size();
                return pointsToAdd;

            case "ArmyCards":
                pointsToAdd=leftOpponent.getArmyEffects().size() + rightOpponent.getScienceEffects().size();
                return pointsToAdd;

            case "ResourceCards":
                pointsToAdd=leftOpponent.getResourceEffects().size() + rightOpponent.getResourceEffects().size();
                return pointsToAdd;

            case "Defeats":
                pointsToAdd=leftOpponent.getNegativeArmyPoints() + rightOpponent.getNegativeArmyPoints();
                return pointsToAdd;

            case "ResourceGoodGuildCards":
                pointsToAdd=player.getResourceEffects().size() + player.getGoodEffects().size() + player.getGuildEffects().size();
                return pointsToAdd;
            case "ChooseSymbol":

                break;
        }
        return 0;
    }
    public String getGuildEffectType() {
        return guildEffectType;
    }

}
