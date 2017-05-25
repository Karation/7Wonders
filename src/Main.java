
import board.Board;
import cards.Deck;
import gui.InitialGui;
import gui.WonderChoiceThread;
import player.Player;
import wonders.Wonders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private Board board;
    private List<WonderChoiceThread> threads = new ArrayList<>();
    private ArrayList<Wonders> chosenWonders=new ArrayList<>();
    private ArrayList<Player> players= new ArrayList<>();
    private static final String FILEPATH = "cards1.txt";
    private static int numOfPlayers =3 ;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Main().start();
    }

    private void start() throws IOException, InterruptedException {
        Deck deck = new Deck();
        createBoardAndPlayers();
        deck.dealCards(deck.shuffle(deck.loadCards(FILEPATH)), players);
        dealAndChooseWonders();

        for(int i = 0; i < 3; i++){
            Player player=players.get(i);
            new InitialGui(player, player.getCardsInHand(), player.getWonder());
        }
    }

    private void dealAndChooseWonders() throws InterruptedException {
        ArrayList<ArrayList<Wonders>> wonders = Wonders.addWondersToArray();
        wonders =Wonders.shuffleWonders(wonders);
        for (int i = 0; i < 3; i++) {
            ArrayList<Wonders> wonderSides = Wonders.dealWonders(wonders);
            WonderChoiceThread thread = new WonderChoiceThread(wonderSides, chosenWonders);
            thread.start();
            threads.add(thread);

        }
        while(chosenWonders.size()<3){
            Thread.sleep(2000);
            System.out.println("czekam");
        }
        for (WonderChoiceThread thread : threads) {
            thread.join();
        }
        for (int i = 0; i < 3; i++) {
            Player player=players.get(i);
            player.setBoard(board);
            Wonders wonder=chosenWonders.get(i);
            player.setWonder(wonder);

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
