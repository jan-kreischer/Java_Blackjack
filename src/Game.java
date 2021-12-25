import java.util.Objects;
import java.util.Scanner;

public class Game implements Observer {
    public Player player;
    public Dealer dealer;
    public Deck deck;
    public int betAmount;

    //com.group17.Game Constructor
    public Game() {
        player = new Player(); // The player Object is generated
        dealer = new Dealer(); // The dealer Object is generated
        deck = new Deck(); // The com.group17.Deck Object is generated

        player.addObserver(this); // Add the Game as an observer both to player
        dealer.addObserver(this); // and dealer.
    }
// Ask for new round handles everything that has to be done at the beginning of a round e.g asking if the
// player wants to play, getting the bet amount, checking if the player has enough money to play.
    public boolean askForNewRound() {
        if (!player.checkEnoughMoney(0)) {
            return false;
        }
        Scanner InputReader = new Scanner(System.in);
        System.out.println("Do you want to play? (Press y for yes and n for no): ");
        String AnswerWantToPlay = "a";
        while (!(AnswerWantToPlay.equals("y") || AnswerWantToPlay.equals("n"))) { //asks for user input until user enters y or n.
            AnswerWantToPlay = InputReader.nextLine();
        }
        if (AnswerWantToPlay.equals("n")) { // if user presses n, askForNewRound stops
            System.out.println("Bye");
            return false;
        }
        System.out.println("You have " + player.money + " coins");
        System.out.println("How much do you want to bet?");
        // Asking for Bet and verifying if User has enough money:
        while (true) { //stays in the loop until the player gives a valid bet amount
            while (!InputReader.hasNextInt()) {
                System.out.println("Please enter a number: ");
                InputReader.next(); // discard next token, which isn't a valid int
            }
            betAmount = InputReader.nextInt();
            if (player.checkEnoughMoney(betAmount) && betAmount>=0) {// conditions for a valid betAmount
                break;
            }
            else if( betAmount<0){
                System.out.println("You can't bet a negative amount");
            }
            else if(player.checkEnoughMoney(betAmount)==false) {
                System.out.println("You don't have enough money to bet that amount.");
            }
        }

        System.out.println("Thank you! I received " + betAmount + " coins.");
        return true;
    }

    private boolean userInputForHorS() {//Aks the users if they want to hit or stay

        Scanner InputReader = new Scanner(System.in);
        String Answer = "";
        System.out.println("Do you want to hit or stay? (Enter h or s): ");
        while (!(Answer.equals("h") || Answer.equals("s"))) { //asks for user input until user entes h or s.
            Answer = InputReader.nextLine();
        }
        if (Objects.equals(Answer, "s")) { // if user presses n, askForNewRound stops
            return false; // false means stay
        }

        return true; // true means hit
    }

    public boolean hit() { // false means bust, true means still under 21
        player.receiveCard(deck.drawCard(), true);
        if (player.cardValue > 21) {
            return false;
        }
        return true;
    }

    public boolean stay() { // if it returns false: dealer busts
        while (dealer.cardValue < 17) {
            dealer.receiveCard(deck.drawCard(), true);
        }
        if (dealer.cardValue > 21) {
            return false;
        }
        return true;
    }


    public String printTable(boolean noHiddenCards) { // true means show all cards of the dealer, false: all except first card of dealer

        // Build the printable-String
        //Header
        String printable = "-------------------------------------------------------- Cards of the dealer --------------------------------------------------------\n";

        // Figure out if the second card is upside down
        if (noHiddenCards) {
            printable += dealer.printCardsInclFirstImage(); // Dealer Cards, no card upside down
        } else {
            printable += dealer.printCardsImage(); // Dealer Cards, second card upside down
        }

        // Header
        printable += "\n------------------------------------------------------------- Your Cards -------------------------------------------------------------\n";

        printable += player.printCardsImage(); // Player Cards

        // Displays current amount of money and bet amount
        printable += "\n--------------------------------------- You own " + player.money +" coins, currently you're betting for " + betAmount + " coins ---------------------------------------\n";

        // print and return the String
        System.out.println(printable);
        return printable;
    }


    public void resetCards() {
        // We return all the cards to their original starting state
        player.resetCurrentCards();
        dealer.resetCurrentCards();
        deck.resetDeck();
    }

    public void firstTimeDrawingCards() {
        // The player and the dealer get 2 cards each
        dealer.receiveCard(deck.drawCard(), false);
        dealer.receiveCard(deck.drawCard(), false);
        player.receiveCard(deck.drawCard(), false);
        player.receiveCard(deck.drawCard(), true);
    }


    void gameProcess() { //runs the whole game
        while (true) {
            if (!askForNewRound()) {
                break;
            } //If the player doesn't want to or can't play another round we exit this loop

            resetCards(); // We always want to start the round with a new configuration of cards
            firstTimeDrawingCards(); // To begin both people draw two cards

            boolean notYetBustPlayer = true; // We want to realize once the player burst
            while (notYetBustPlayer) {
                if (!userInputForHorS()) {
                    break;
                } // userInputForHorS is False when we want to stay and therefore break the loop
                notYetBustPlayer = hit(); // Hit gives us as output if the player burst already
            }
            if (notYetBustPlayer) { //I f the player hasn't burst yet, and wants to stay
                stay();
            }

            System.out.println(winningOutput(checkForWinner())); // We check who's won and
                                                // update the money and give appropriate output

        }
    }

    public String winningOutput(String result){ // Return the string that needs to be printed depending on who has won
        printTable(true);
        if (Objects.equals(result, "w")){
            player.updateMoney(betAmount);
            return "You have won the game, you'll receive " + betAmount + " coins\nCurrently you own " + player.money +" coins";}

        else if(Objects.equals(result, "l")) {
            player.updateMoney(-betAmount);
            return "You lost the game.\nCurrently you own " + player.money +" coins";}

        else{
            return "The game ended in a draw.\nCurrently you own " + player.money +" coins";}
    }

    public String checkForWinner() { // if player wins return: w, lose: l, draw: d

        if (player.cardValue > 21) {
            return "l";
        } else if (dealer.cardValue > 21) {
            return "w";
        }
        int maxValPlayer = player.checkForAces(); // Checks wheter it is better for the player if Ace is counted as 11 or as 1
        int maxValDealer = dealer.checkForAces();

        if (maxValPlayer == maxValDealer) {
            return "d";
        }
        if (maxValPlayer > maxValDealer) {
            return "w";
        }
        return "l";
    }

}




