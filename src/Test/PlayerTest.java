package com.group17.Test;

import com.group17.Card;
import com.group17.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {


    @Test
    public void checkEnoughMoneyTest1(){
        Player player = new Player();

        boolean actual = player.checkEnoughMoney(110);
        boolean expected = false;
        assertEquals(expected,actual);
        assertEquals(100,player.money);
    }

    @Test
    public void checkEnoughMoneyWithZeroTest(){
        Player player = new Player();
        player.money = 0;
        boolean actual = player.checkEnoughMoney(0);
        boolean expected = false;
        assertEquals(expected,actual);
    }
    @Test
    public void checkEnoughMoneyTest2(){
        Player player = new Player();

        boolean actual = player.checkEnoughMoney(10);
        boolean expected = true;
        assertEquals(expected,actual);
        assertEquals(100,player.money);

    }

    @Test
    public void checkForAcesTest1(){
        // check with 1 Ace
        Player player = new Player();
        Card card1 = new Card("Hearts","Ace");
        Card card2 = new Card("Hearts","5");
        player.receiveCard(card1, false);
        player.receiveCard(card2, false);
        int actual = player.checkForAces();
        int expected = 16;
        assertEquals(expected,actual);
    }

    @Test
    public void checkForAcesTest2(){
        // check with 3 Aces
        Player player = new Player();
        Card card1 = new Card("Hearts","Ace");
        Card card2 = new Card("Hearts","5");
        Card card3 = new Card("Spades", "Ace");
        Card card4 = new Card("Diamonds", "Ace");
        player.receiveCard(card1, false);
        player.receiveCard(card2, false);
        player.receiveCard(card3,false);
        player.receiveCard(card4,false);
        int actual = player.checkForAces();
        int expected = 18;
        assertEquals(expected,actual);
    }
    @Test
    public void checkForAcesTest3(){
        // check with no Aces
        Player player = new Player();
        Card card1 = new Card("Hearts","3");
        Card card2 = new Card("Hearts","5");
        Card card3 = new Card("Spades", "Jack");
        player.receiveCard(card1, false);
        player.receiveCard(card2, false);
        player.receiveCard(card3,false);
        int actual = player.checkForAces();
        int expected = 18;
        assertEquals(expected,actual);
    }
    @Test
    public void checkForAcesTest4(){
        // check with a bust
        Player player = new Player();
        Card card1 = new Card("Hearts","9");
        Card card2 = new Card("Hearts","5");
        Card card3 = new Card("Spades", "Ace");
        Card card4 = new Card("Diamonds", "7");
        player.receiveCard(card1, false);
        player.receiveCard(card2, false);
        player.receiveCard(card3,false);
        player.receiveCard(card4,true);
        int actual = player.checkForAces();
        int expected = 0;
        assertEquals(expected,actual);
    }

    @Test
    public void printCardsImageTest(){
        Player player = new Player();
        Card card1 = new Card("Spades", "Jack");
        Card card2 = new Card("Diamonds", "7");
        player.receiveCard(card1, false);
        player.receiveCard(card2, false);
        String actual = player.printCardsImage();
        String expected="┌─────────┐┌─────────┐\n"+"│J        ││7        │\n"+"│         ││         │\n"+"│    ♠    ││    ♦    │\n"+"│         ││         │\n"+"│        J││        7│\n"+"└─────────┘└─────────┘\n";
        assertEquals(expected,actual);
    }

    @Test
    public void printCardsImageTestIfEmpty(){
        Player player = new Player();
        String actual = player.printCardsImage();
        String expected = "|| No cards ||";
        assertEquals(expected,actual);
    }

    @Test
    public void resetCurrentCardsTest(){
        Player player = new Player();
        player.receiveCard(new Card("Hearts", "5"), false);
        player.resetCurrentCards();

        assertEquals(0,player.cardValue);
        assertEquals(0, player.cardsOnTable.size());
    }

}
