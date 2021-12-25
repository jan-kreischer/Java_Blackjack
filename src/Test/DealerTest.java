package com.group17.Test;

import com.group17.Card;
import com.group17.Dealer;
import com.group17.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerTest {


    @Test
    public void checkForAcesTest1(){
        // check with 1 Ace
        Dealer dealer = new Dealer();
        Card card1 = new Card("Hearts","Ace");
        Card card2 = new Card("Hearts","5");
        dealer.receiveCard(card1, false);
        dealer.receiveCard(card2, false);
        int actual = dealer.checkForAces();
        int expected = 16;
        assertEquals(expected,actual);
    }

    @Test
    public void checkForAcesTest2(){
        // check with 3 Aces
        Dealer dealer = new Dealer();
        Card card1 = new Card("Hearts","Ace");
        Card card2 = new Card("Hearts","5");
        Card card3 = new Card("Spades", "Ace");
        Card card4 = new Card("Diamonds", "Ace");
        dealer.receiveCard(card1, false);
        dealer.receiveCard(card2, false);
        dealer.receiveCard(card3,false);
        dealer.receiveCard(card4,false);
        int actual = dealer.checkForAces();
        int expected = 18;
        assertEquals(expected,actual);
    }
    @Test
    public void checkForAcesTest3(){
        // check with no Aces
        Dealer dealer = new Dealer();
        Card card1 = new Card("Hearts","3");
        Card card2 = new Card("Hearts","5");
        Card card3 = new Card("Spades", "Jack");
        dealer.receiveCard(card1, false);
        dealer.receiveCard(card2, false);
        dealer.receiveCard(card3,false);
        int actual = dealer.checkForAces();
        int expected = 18;
        assertEquals(expected,actual);
    }
    @Test
    public void checkForAcesTest4(){
        // check with a bust
        Dealer dealer = new Dealer();
        Card card1 = new Card("Hearts","9");
        Card card2 = new Card("Hearts","5");
        Card card3 = new Card("Spades", "Ace");
        Card card4 = new Card("Diamonds", "7");
        dealer.receiveCard(card1, false);
        dealer.receiveCard(card2, false);
        dealer.receiveCard(card3,false);
        dealer.receiveCard(card4,true);
        int actual = dealer.checkForAces();
        int expected = 0;
        assertEquals(expected,actual);
    }


    @Test
    public void printCardsInclFirstImageTest(){
        Dealer dealer = new Dealer();
        Card card1 = new Card("Spades", "Jack");
        Card card2 = new Card("Diamonds", "7");
        dealer.receiveCard(card1, false);
        dealer.receiveCard(card2, false);
        String actual = dealer.printCardsInclFirstImage();
        String expected="┌─────────┐┌─────────┐\n"+"│J        ││7        │\n"+"│         ││         │\n"+"│    ♠    ││    ♦    │\n"+"│         ││         │\n"+"│        J││        7│\n"+"└─────────┘└─────────┘\n";
        assertEquals(expected,actual);
    }

    @Test
    public void printCardsImageTest(){
        Dealer dealer = new Dealer();
        Card card1 = new Card("Spades", "Jack");
        Card card2 = new Card("Diamonds", "7");
        dealer.receiveCard(card1, false);
        dealer.receiveCard(card2, false);
        String actual = dealer.printCardsImage();
        String expected ="┌─────────┐┌─────────┐\n"+"│J        ││░░░░░░░░░│\n"+"│         ││░░░░░░░░░│\n"+"│    ♠    ││░░░░░░░░░│\n"+"│         ││░░░░░░░░░│\n"+"│        J││░░░░░░░░░│\n"+"└─────────┘└─────────┘\n";
        assertEquals(expected,actual);
    }

    @Test
    public void printCardsImageTestIfEmpty(){
        Dealer dealer = new Dealer();
        String actual = dealer.printCardsImage();
        String expected = "|| No cards ||";
        assertEquals(expected,actual);
    }

    @Test
    public void printCardsInclFirstImageTestIfEmpty(){
        Dealer dealer = new Dealer();
        String actual = dealer.printCardsInclFirstImage();
        String expected = "|| No cards ||";
        assertEquals(expected,actual);
    }

    @Test
    public void resetCurrentCardsTest(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card("Hears", "5"), false);
        dealer.resetCurrentCards();

        assertEquals(0,dealer.cardValue);
        assertEquals(0, dealer.cardsOnTable.size());
    }
}
