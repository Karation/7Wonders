package effects;

public class WonderEffect extends Effect{
    private String effectName;

    public WonderEffect(String effectType, String effectName) {
        super(effectType);
        this.effectName = effectName;
    }

//    public void resolveWonderEffect(WonderEffect effect, Player player){
//        String effectName = effect.getEffectName();
//        switch (effectName){
//            case "ResourceTrade":
//
//                break;
//            case "CopyGuild":
//
//                break;
//            case "FreeBuild":
//
//                break;
//            case "BuildFromGrave":
//                Board board = player.getBoard();
//                ArrayList<Card> cardsInGrave = board.getCardsInGrave();
//
//                break;
//            case "ResourceProduction":
//
//                break;
//            case "GoodProduction":
//
//                break;
//            case "ChooseScienceSymbol":
//
//                break;
//            case "UseLastCard":
//
//                break;
//        }
//    }

    public String getEffectName() {
        return effectName;
    }
}
