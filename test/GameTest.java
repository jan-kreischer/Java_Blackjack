import com.group17.Card;
import com.group17.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game currentGame = new Game();

    @Test
    public void winningOutputTestPlayerWon(){
        currentGame.betAmount = 20;
        assertEquals(currentGame.winningOutput("w"), "You have won the game, you'll receive 20 coins\nCurrently you own 120 coins");
    }
    @Test
    public void winningOutputTestPlayerLost(){
        currentGame.betAmount = 20;
        assertEquals(currentGame.winningOutput("l"), "You lost the game.\nCurrently you own 80 coins");
    }
    @Test
    public void winningOutputTestDraw(){
        currentGame.betAmount = 20;
        assertEquals(currentGame.winningOutput("d"), "The game ended in a draw.\nCurrently you own 100 coins");
    }
    @Test
    public void stayTestBust(){
        currentGame.dealer.cardValue=22;
        assertEquals(false,currentGame.stay());
    }
    @Test
    public void stayTestDealerOk(){
        currentGame.dealer.cardValue=18;
        assertEquals(true, currentGame.stay());
    }
    @Test
    public void stayDealerDrawsCard(){
        currentGame.dealer.cardValue=15; //Whatever we draw we have surpassed 17
        Boolean NotBurst = currentGame.stay();
        if (currentGame.dealer.cardValue <= 21) {
            assertTrue(NotBurst); // no burst
        }
        else{
            assertFalse(NotBurst); // Burst
        }
    }
    @Test
    public void hitTestBust(){
        currentGame.player.cardValue= 22;
        assertEquals(false, currentGame.hit());
    }

    @Test
    public void hitTestPlayerOK(){
        currentGame.player.cardValue = 2;
        assertEquals(true, currentGame.hit());

    }

    @Test
    public void PlayerEnoughMoney(){
        currentGame.player.money= 0;
        assertEquals(false, currentGame.askForNewRound());
    }


    @Test
    public void twoCards(){ // firstTimeDrawingCard Tested
        boolean v = true;
        currentGame.player.cardsOnTable.clear();
        currentGame.dealer.cardsOnTable.clear();
        currentGame.firstTimeDrawingCards();

        assertEquals(2, currentGame.player.cardsOnTable.size());
        assertEquals(2, currentGame.dealer.cardsOnTable.size());

    }
    @Test
    public void resetCardsTest(){
        currentGame.resetCards();
        assertTrue( currentGame.player.cardsOnTable.isEmpty()&&currentGame.dealer.cardsOnTable.isEmpty());


    }
    @Test
    public void checkForWinnerTest(){
        //Test 1 Player bursts
        currentGame.player.cardValue = 22;
        assertEquals("l", currentGame.checkForWinner()); // Player bursts

        // Test 2 Dealer burst
        currentGame.player.cardValue = 20;
        currentGame.dealer.cardValue = 22;
        assertEquals("w", currentGame.checkForWinner());

        // Test 3 Draw
        Card card = new Card("Hearts", "2");
        currentGame.player.resetCurrentCards();
        currentGame.dealer.resetCurrentCards();
        currentGame.player.receiveCard(card, false);
        currentGame.dealer.receiveCard(card, false);
        assertEquals("d", currentGame.checkForWinner());

        // Test 4 player won with closer to 21
        card = new Card("Hearts", "3");
        currentGame.player.receiveCard(card, false);
        assertEquals("w", currentGame.checkForWinner());

        // Test 5 dealer won with closer to 21
        currentGame.dealer.receiveCard(card, false);
        currentGame.dealer.receiveCard(card, false);
        assertEquals("l", currentGame.checkForWinner());

        // Test 6 Aces are handled correctly
        card = new Card("Hearts", "Ace");
        currentGame.player.receiveCard(card, false);
        assertEquals("w", currentGame.checkForWinner());

        // Test 7 Aces are handled correctly part 2
        currentGame.player.resetCurrentCards();
        currentGame.dealer.resetCurrentCards();

        currentGame.player.receiveCard(card, false);
        currentGame.player.receiveCard(card, false);

        card = new Card("Hearts", "King");
        currentGame.dealer.receiveCard(card, false);
        currentGame.dealer.receiveCard(card, false);

        card = new Card("Hearts", "9");
        currentGame.player.receiveCard(card, false);

        // Cards of player {Ace, Ace, 9} optimal configuration is 21
        // Cards of dealer {King, King} optimal configuration is 20

        assertEquals("w", currentGame.checkForWinner());

        // Print Table Test 1-2

    }

    @Test
    public void printTableTest() {

        // Control the hand cards of both player and dealer
        currentGame.betAmount =0;
        currentGame.player.money = 100;
        currentGame.player.resetCurrentCards();
        currentGame.dealer.resetCurrentCards();
        Card card =new Card("Hearts", "Ace");

        currentGame.player.receiveCard(card, false);
        currentGame.player.receiveCard(card, false);

        card = new Card("Hearts", "King");
        currentGame.dealer.receiveCard(card, false);
        currentGame.dealer.receiveCard(card, false);

        card = new Card("Hearts", "9");
        currentGame.player.receiveCard(card, false);

        assertEquals( "-------------------------------------------------------- Cards of the dealer --------------------------------------------------------\n┌─────────┐┌─────────┐\n│K        ││K        │\n│         ││         │\n│    ♥    ││    ♥    │\n│         ││         │\n│        K││        K│\n└─────────┘└─────────┘\n\n------------------------------------------------------------- Your Cards -------------------------------------------------------------\n┌─────────┐┌─────────┐┌─────────┐\n│A        ││A        ││9        │\n│         ││         ││         │\n│    ♥    ││    ♥    ││    ♥    │\n│         ││         ││         │\n│        A││        A││        9│\n└─────────┘└─────────┘└─────────┘\n\n--------------------------------------- You own 100 coins, currently you're betting for 0 coins ---------------------------------------\n", currentGame.printTable(true));
        assertEquals("-------------------------------------------------------- Cards of the dealer --------------------------------------------------------\n┌─────────┐┌─────────┐\n│K        ││░░░░░░░░░│\n│         ││░░░░░░░░░│\n│    ♥    ││░░░░░░░░░│\n│         ││░░░░░░░░░│\n│        K││░░░░░░░░░│\n└─────────┘└─────────┘\n\n------------------------------------------------------------- Your Cards -------------------------------------------------------------\n┌─────────┐┌─────────┐┌─────────┐\n│A        ││A        ││9        │\n│         ││         ││         │\n│    ♥    ││    ♥    ││    ♥    │\n│         ││         ││         │\n│        A││        A││        9│\n└─────────┘└─────────┘└─────────┘\n\n--------------------------------------- You own 100 coins, currently you're betting for 0 coins ---------------------------------------\n", currentGame.printTable(false));




    }
}
