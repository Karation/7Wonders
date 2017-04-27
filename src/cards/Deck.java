package cards;

import board.Board;
import player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class Deck {
    public ArrayList<Card> shuffle(ArrayList<Card> cards) {
        for (int i = cards.size() - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(rand));
            cards.set(rand, temp);
        }
        return cards;
    }

    public void dealCards(ArrayList<Card> shuffledDeck, ArrayList<Player> players) {
        int cardCount=0;
        while (cardCount<shuffledDeck.size()) {
            for (int j = 0; j < players.size(); j++) {
                ArrayList<Card> cardsInHand = new ArrayList<>();
                int current = 0;
                while(current< Player.MAX_CARDS_IN_HAND){
                    cardsInHand.add(shuffledDeck.get(cardCount));
                    cardCount++;
                    current++;
                }
                players.get(j).setCardsInHand(cardsInHand);
            }
        }
        for (int i = 0; i <players.size() ; i++) {

            Player leftPlayer;
            Player rightPlayer;
            leftPlayer=players.get(i).getLeftPlayerFromArray(players, i);
            rightPlayer=players.get(i).getRightPlayerFromArray(players, i);
            players.get(i).setLeftPlayer(leftPlayer);
            players.get(i).setRightPlayer(rightPlayer);
        }
    }

    public ArrayList<Card> loadCards(String filepath) throws IOException {


        ArrayList<Card> cards=new ArrayList<>();
        String name;
        String type;
        String effect;
        String price;
        Integer minimumPlayers;
        String canBuild;
        String canBuild2;
        String picturePath;
        File file=new File(filepath);
        FileInputStream fis=null;
        Scanner scan=null;

        try{
            fis= new FileInputStream(file);
            scan=new Scanner(file);
            while (scan.hasNextLine()){
                while(scan.hasNext()) {
                    name=scan.next();
                    type=scan.next();
                    effect=scan.next();
                    price=scan.next();
                    minimumPlayers=scan.nextInt();
                    canBuild=scan.next();
                    canBuild2=scan.next();
                    picturePath=scan.next();
                    Image picture = ImageIO.read(new File(picturePath));

                    Card card= CardsFactory.create(type, name, price, minimumPlayers,picture, effect, canBuild, canBuild2);
                    cards.add(card);
                }
                scan.nextLine();
            }
            //scan.close();
        }
        catch(IOException io){
            System.out.println("File not found");
        }
        finally {
            fis.close();
            scan.close();
            return cards;
        }
    }
    public ArrayList<Card> addGuildsToDeck(ArrayList<Card> guilds, ArrayList<Card> cards, int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers + 2; i++) {
            cards.add(guilds.get(i));
        }
        return cards;
    }
}
