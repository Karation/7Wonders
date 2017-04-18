
import board.Board;
import cards.Card;
import cards.Deck;
import effects.Effect;
import gui.InitialGui;
import gui.WonderChoiceThread;
import player.Player;
import wonders.Wonders;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    InitialGui initialGui;
    Deck deck;
    Board board;
    ArrayList<ArrayList<Wonders>> wonders;
    ArrayList<Wonders> wonderSides;
    List<WonderChoiceThread> threads = new ArrayList<>();
    ArrayList<Wonders> chosenWonders=new ArrayList<>();
    ArrayList<Player> players= new ArrayList<>();
    String filepath = "cards1.txt";
    int numOfPlayers =3 ;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Main().start();
    }

    public void start() throws IOException, InterruptedException {
        deck = new Deck();
        createBoardAndPlayers();
        deck.dealCards(deck.shuffle(deck.loadCards(filepath)), players);
        wonders= Wonders.addWondersToArray();
        wonders=Wonders.shuffleWonders(wonders);
        for (int i = 0; i < 3; i++) {
            wonderSides=Wonders.dealWonders(wonders);
            WonderChoiceThread thread = new WonderChoiceThread(wonderSides, chosenWonders);
            thread.start();
            threads.add(thread);

        }
        while(chosenWonders.size()<3){
            Thread.sleep(2000);
            System.out.println("czekam");
        }
        for (int i = 0; i < 3; i++) {
            Player player=players.get(i);
            player.setBoard(board);
            Wonders wonder=chosenWonders.get(i);
            player.setWonder(wonder);

        }
        for(int i = 0; i < 3; i++){
            Player player=players.get(i);
            initialGui = new InitialGui(player, player.getCardsInHand(), player.getWonder());
        }
    }

    private void createBoardAndPlayers() {
        for (int i = 0; i < numOfPlayers; i++) {
            Player player = new Player();
            players.add(player);
        }
        board = new Board(players);
    }
}
