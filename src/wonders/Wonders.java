package wonders;

import effects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Wonders {

    ALEXANDRIA_A(new WondersBuilder("Alexandria", EffectFactory.create("Good", "Glass"), "WondersPictures\\AlexandriaA.jpg")
            .addEffect(new WonderStage(1, "SS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "OO", new ArrayList<>(Arrays.asList(EffectFactory.create("Trade", "ResourceProduction")))))
            .addEffect(new WonderStage(3, "GG", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    ALEXANDRIA_B(new WondersBuilder("Alexandria", EffectFactory.create("Good", "Glass"), "WondersPictures\\AlexandriaB.jpg")
            .addEffect(new WonderStage(1, "ZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Trade", "ResourceProduction")))))
            .addEffect(new WonderStage(2, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Trade", "GoodProduction")))))
            .addEffect(new WonderStage(3, "SSS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    EPHESOS_A(new WondersBuilder("Ephesos", EffectFactory.create("Good", "Paper"), "WondersPictures\\EphesosA.jpg")
            .addEffect(new WonderStage(1, "SS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Money", "9")))))
            .addEffect(new WonderStage(3, "PP", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    EPHESOS_B(new WondersBuilder("Ephesos", EffectFactory.create("Good", "Paper"), "WondersPictures\\EphesosB.jpg")
            .addEffect(new WonderStage(1, "SS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "2"), EffectFactory.create("Money", "4")))))
            .addEffect(new WonderStage(2, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3"), EffectFactory.create("Money", "4")))))
            .addEffect(new WonderStage(3, "PCG", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "5"), EffectFactory.create("Money", "4")))))),
    RHODOS_A(new WondersBuilder("Rhodos", EffectFactory.create("Resource", "Ore"), "WondersPictures\\RhodosA.jpg")
            .addEffect(new WonderStage(1, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "ZZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Army", "2")))))
            .addEffect(new WonderStage(3, "OOOO", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    RHODOS_B(new WondersBuilder("Rhodos", EffectFactory.create("Resource", "Ore"), "WondersPictures\\RhodosB.jpg")
            .addEffect(new WonderStage(1, "SSS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3"), EffectFactory.create("Army", "1"), EffectFactory.create("Money", "3")))))
            .addEffect(new WonderStage(2, "OOOO", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "4"), EffectFactory.create("Army", "1"), EffectFactory.create("Money", "4")))))),
    OLYMPIA_A(new WondersBuilder("Olympia", EffectFactory.create("Resource", "Wood"), "WondersPictures\\OlympiaA.jpg")
            .addEffect(new WonderStage(1, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "SS", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "BuildForFree")))))
            .addEffect(new WonderStage(3, "OO", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    OLYMPIA_B(new WondersBuilder("Olympia", EffectFactory.create("Resource", "Wood"), "WondersPictures\\OlympiaB.jpg")
            .addEffect(new WonderStage(1, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Trade", "ResourceTrade")))))
            .addEffect(new WonderStage(2, "SS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "5")))))
            .addEffect(new WonderStage(3, "COO", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "CopyGuild")))))),
    GIZA_A(new WondersBuilder("Giza", EffectFactory.create("Resource", "Stone"), "WondersPictures\\GizaA.jpg")
            .addEffect(new WonderStage(1, "SS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "WWW", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "5")))))
            .addEffect(new WonderStage(3, "SSSS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    GIZA_B(new WondersBuilder("Giza", EffectFactory.create("Resource", "Stone"), "WondersPictures\\GizaB.jpg")
            .addEffect(new WonderStage(1, "WW", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "SSS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "5")))))
            .addEffect(new WonderStage(3, "ZZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "5")))))
            .addEffect(new WonderStage(4, "CSSSS", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    HALICARNASSOS_A(new WondersBuilder("Halicarnassos", EffectFactory.create("Good", "Cloth"), "WondersPictures\\HalicarnassosA.jpg")
            .addEffect(new WonderStage(1, "ZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "OOO", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "BuildFromGrave")))))
            .addEffect(new WonderStage(3, "CC", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    HALICARNASSOS_B(new WondersBuilder("Halicarnassos", EffectFactory.create("Good", "Cloth"), "WondersPictures\\HalicarnassosB.jpg")
            .addEffect(new WonderStage(1, "OO", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "2"),EffectFactory.create("WonderEffect", "BuildFromGrave")))))
            .addEffect(new WonderStage(2, "ZZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "1"),EffectFactory.create("WonderEffect", "BuildFromGrave")))))
            .addEffect(new WonderStage(3, "GCP", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "BuildFromGrave")))))),
    BABYLON_A(new WondersBuilder("Babylon", EffectFactory.create("Resource", "Clay"), "WondersPictures\\BabylonA.jpg")
            .addEffect(new WonderStage(1, "ZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "WWW", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "ChooseScienceSymbol")))))
            .addEffect(new WonderStage(3, "ZZZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "7")))))),
    BABYLON_B(new WondersBuilder("Babylon", EffectFactory.create("Resource", "Clay"), "WondersPictures\\BabylonB.jpg")
            .addEffect(new WonderStage(1, "CZ", new ArrayList<>(Arrays.asList(EffectFactory.create("Culture", "3")))))
            .addEffect(new WonderStage(2, "GWW", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "UseLastCard")))))
            .addEffect(new WonderStage(3, "PZZZ", new ArrayList<>(Arrays.asList(EffectFactory.create("WonderEffect", "ChooseScienceSymbol"))))));

    private String name;
    private List<WonderStage> wonderStages;
    private Effect production;
    private String imagePath;

    public static void main(String[] args){addWondersToArray();}


    private Wonders(WondersBuilder builder){
        this.name = builder.name;
        this.production = builder.production;
        this.imagePath = builder.imagePath;
        this.wonderStages = builder.wonderStages;
    }

    public String getName() {
        return this.name;
    }

    public Effect getProduction() {
        return this.production;
    }
    public String getImagePath(){ return this.imagePath;}

    public List<WonderStage> getWonderStages() {
        return wonderStages;
    }

    public static ArrayList<ArrayList<Wonders>> addWondersToArray(){

        ArrayList<ArrayList<Wonders>> wonders = new ArrayList<>();

        ArrayList<Wonders> ephesosSides = new ArrayList<>(Arrays.asList(EPHESOS_A, EPHESOS_B));
        ArrayList<Wonders> alexandrySides = new ArrayList<>(Arrays.asList(ALEXANDRIA_A, ALEXANDRIA_B));
        ArrayList<Wonders> rhodosSides = new ArrayList<>(Arrays.asList(RHODOS_A, RHODOS_B));
        ArrayList<Wonders> babylonSides = new ArrayList<>(Arrays.asList(BABYLON_A, BABYLON_B));
        ArrayList<Wonders> gizaSides = new ArrayList<>(Arrays.asList(GIZA_A, GIZA_B));
        ArrayList<Wonders> halicarnassosSides = new ArrayList<>(Arrays.asList(HALICARNASSOS_A, HALICARNASSOS_B));
        ArrayList<Wonders> olympiaSides = new ArrayList<>(Arrays.asList(OLYMPIA_A, OLYMPIA_B));

        wonders.add(alexandrySides);
        wonders.add(ephesosSides);
        wonders.add(rhodosSides);
        wonders.add(babylonSides);
        wonders.add(gizaSides);
        wonders.add(halicarnassosSides);
        wonders.add(olympiaSides);

        return wonders;
    }
    public static ArrayList<ArrayList<Wonders>> shuffleWonders(ArrayList<ArrayList<Wonders>> wonders){
        for (int i = wonders.size() - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            ArrayList<Wonders> temp = wonders.get(i);
            wonders.set(i, wonders.get(rand));
            wonders.set(rand, temp);
        }
        return wonders;
    }
    public static ArrayList<Wonders> dealWonders(ArrayList<ArrayList<Wonders>> wonders){
        ArrayList<Wonders> wonder =wonders.get(0);
        wonders.remove(0);
        return wonder;
    }
    public WonderStage checkWhichWonderStageBuild(){
        ArrayList<WonderStage> stages = (ArrayList<WonderStage>) this.getWonderStages();
        WonderStage stageToBuild;
        for (int i = 0; i < stages.size(); i++) {
            if (!stages.get(i).IsBuilt()){
                stageToBuild=stages.get(i);
                return stageToBuild;
            }
        }
        return null;
    }

    public static class WondersBuilder {
        private final String name;
        private final Effect production;
        private final String imagePath;
        private List<WonderStage> wonderStages = new ArrayList<>();

        public WondersBuilder(String name, Effect production, String imagePath){
            this.name = name;
            this.production = production;
            this.imagePath=imagePath;
        }

        WondersBuilder addEffect(WonderStage effect) {
            this.wonderStages.add(effect);
            return this;
        }

        WondersBuilder addEffects(WonderStage... effect) {
            this.wonderStages.addAll(wonderStages);
            return this;
        }

    }
}
