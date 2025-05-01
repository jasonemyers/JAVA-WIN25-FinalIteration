import java.util.*;
import java.util.ArrayList;


interface PokerGameRules { //Interface/Abstract Class
    void startRound();
    void playerAction(int Action);
    void evaluateHands();
}

abstract class Player { //Abstract Class and Parent Class
    protected String name; //Implementation
    protected ArrayList<String> hand = new ArrayList<>(); //Intialize hand which stores the player's cards. Utilizing ArrayList.
    protected int chips;

    public Player(String name, int chips) { //Unique Constructor, which other Classes will use 'Super' to update it's params.
        this.name = name;
        this.chips = chips;
    }
    public abstract void play(); //Abstract method(s), below
    public String getName() {
        return name;
    }
    public ArrayList<String> getHand() {
        return hand;
    }
    public int getChips() {
        return chips;
    }
    public void addCard(String card) {
        hand.add(card);
    }
    public void place_bet(int amount) {
        chips -= amount;
    }
    public void win(int amount) {
        chips += amount;
    }
    public void resetHand() {
        hand.clear();
    }
}

class Human_Player extends Player { //Derived class
    public Human_Player(String name, int chips){
        super(name, chips); //Super, which takes Player's constructor with it's own updated params.
    }
    @Override //Method overriding
    public void play() {
        System.out.println(name + " has joined the table");
    }
}

class Dealer extends Player { //Derived Class
    public Dealer(int chips) {
        super("Dealer", chips); //Utilizing Super constructore for Dealer
    }
    @Override //Method overriding
    public void play() {
        System.out.println("All bets are Closed!");
    }
    public boolean Call(int currentBet, int handStrength) {
        return handStrength > 2 || currentBet <= 50; //Texas-Holdem logic utilizing OR
    }

}

class Deck { //This is the logic behind our given cards.
    private ArrayList<String> cards = new ArrayList<>(); //Intialized cards to hold player's cards
    private static final String[] suits = {"Spade", "Club", "Hearts", "Diamonds"};
    private static final String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public Deck() {
        for (String suit: suits) {
            for (String value : values) {
                cards.add(value + suit); //Provided syntax from w3schools for string concatenation with final declaration
            }
        }
        shuffle();
    }
    public void shuffle() {
        Collections.shuffle(cards); //Java util System function
    }

    public String dealCard() {
        return cards.remove(0); //when dealt removes the card from Hand/Cards
    }
}

class PokerGame implements PokerGameRules {
    private Human_Player player;
    private Dealer dealer;
    private Deck deck;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> communityCards = new ArrayList<>();
    private int pot = 0;
    private Map<String, Integer> chipMap = new HashMap<>(); //Syntax for key-value MAP

    public PokerGame() {
        this.player = new Human_Player("Andrea", 50);
        this.dealer = new Dealer(50);
        this.deck = new Deck();
        chipMap.put(player.getName(), player.getChips());
        chipMap.put(dealer.getName(), dealer.getChips());
    }
    //Finish this
    @Override //Method overriding
    public void startRound() {
        //Reset cards and hands to begin a new round
        player.resetHand();
        dealer.resetHand();
        communityCards.clear();
        pot = 0;

        System.out.println("\n--- New Round Started ---");
        player.addCard(deck.dealCard()); //Deal 2 cards to the user
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard()); //Deal 2 cards to the dealer
        dealer.addCard(deck.dealCard());

        //Enables a mandatory bet that dealer and user must participate in
        pot += 10;
        player.place_bet(5);
        dealer.place_bet(5);

        //System will show players hand but keep dealers hidden
        System.out.println(player.getName() + " hand: " + player.getHand());
        System.out.println("Dealer has been dealt cards."); 
}

    @Override //Method overriding 
    public void playerAction(int action) {
        //Deals with handling each bet and check during a round
        if (action == 1) {
            System.out.println(player.getName() + " checks.");
        } else if (action == 2) {
            System.out.println(player.getName() + " bets 5 chips.");
            player.place_bet(5);
            pot += 5;
        } else {
            System.out.println("Invalid action.");
    }   
}

    @Override //Method overriding
    public void evaluateHands() {
        int playerStrength = (int) (Math.random() * 10);
        int dealerStrength = (int) (Math.random() * 10);

        System.out.println("Player hand strength: " + playerStrength);
        System.out.println("Dealer hand strength: " + dealerStrength);

        if (playerStrength >= dealerStrength) {
            System.out.println(player.getName() + " wins the round and takes " + pot + " chips!");
            player.win(pot);
        } else {
            System.out.println("Dealer wins the round and takes " + pot + " chips!");
            dealer.win(pot);
        }

        chipMap.put(player.getName(), player.getChips());
        chipMap.put(dealer.getName(), dealer.getChips());

        // Show current chip count
        System.out.println("Chip count: " + chipMap);
    }
    public void playGame() { 
        System.out.println("\nWelcome to Texas Hold'em Poker! "); 
        player.play();
        dealer.play(); 
    
        while (player.getChips() > 0 && dealer.getChips() > 0) {
            startRound();
    
            // Player turn
            System.out.println("\nChoose your action:");
            System.out.println("1. Check");
            System.out.println("2. Bet (5 chips)");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            playerAction(choice); 
    
            // Dealer decision
            boolean dealerCalls = dealer.Call(5, (int)(Math.random() * 10));
            if (dealerCalls) {
                System.out.println("Dealer calls!");
                dealer.place_bet(5);
                pot += 5;
            } else {
                System.out.println("Dealer folds!");
            }
    
            evaluateHands(); 
    
        
            // Prompt to continue or quit
            System.out.println("\nPlay another round? (y/n)");
            char continueGame = scanner.next().charAt(0);
            if (continueGame != 'y' && continueGame != 'Y') {
                break;
            }
        }
    
        System.out.println("\nGame over! Final chip count:");
        System.out.println(chipMap); // Display final chip counts
        scanner.close(); 
     
    }
} 
      
public class group_project {  
    // Main entry point for the poker game
    public static void main(String[] args) { 
        PokerGame game = new PokerGame(); // Creates game with player and dealer 
        game.playGame();  // Start the game loop
    }
}
