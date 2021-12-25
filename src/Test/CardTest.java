package com.group17.Test;
import com.group17.*;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardTest {

    //basic value test
    @Test
    public void getValueTest1(){
        Card card = new Card("Heart", "2");
        int actual = card.getValue();
        assertEquals(2,actual );

    }
    //basic value test for Ace
    @Test
    public void getValueTest2(){
        Card card = new Card("Heart", "Ace");
        int actual = card.getValue();
        assertEquals(1,actual );

    }
    //basic value test for Jack and other color
    @Test
    public void getValueTest3(){
        Card card = new Card("Diamonds", "Jack");
        int actual = card.getValue();
        assertEquals(10,actual );

    }

    //basic print card string
    @Test
    public void printSingleCardStringTest1(){
        Card card = new Card("Diamonds", "Jack");
        String actual=card.printSingleCardString();
        String expected="Jack of Diamonds";
        assertEquals(expected, actual);
    }

    //test if card is an Ace
    @Test
    public void isAceTest1(){
        Card card = new Card("Diamonds", "Ace");
        boolean actual=card.isAce();
        boolean expected=true;
        assertEquals(expected, actual);
    }

    //test if card is not an Ace
    @Test
    public void isAceTest2(){
        Card card = new Card("Diamonds", "Jack");
        boolean actual=card.isAce();
        boolean expected=false;
        assertEquals(expected, actual);
    }

    //test print the card upside Down
    @Test
    public void printCardUpSideDownTest(){
        Card card = new Card("Diamonds", "Jack");
        String actual=card.printCardUpSideDown();
        String expected= "\n┌─────────┐\n│░░░░░░░░░│\n│░░░░░░░░░│\n│░░░░░░░░░│\n│░░░░░░░░░│\n│░░░░░░░░░│\n└─────────┘";
        assertEquals(expected, actual);
    }

    //test print Card Heart card
    @Test
    public void printCardImageTest1(){
        Card card = new Card("Hearts", "Jack");
        String actual=card.printCardImage();
        String expected= "\n┌─────────┐\n│J        │\n│         │\n│    ♥    │\n│         │\n│        J│\n└─────────┘";
        assertEquals(expected, actual);
    }
    @Test
    public void printCardImageTest2(){
        Card card = new Card("Diamonds", "10");
        String actual=card.printCardImage();
        String expected= "\n┌─────────┐\n│10       │\n│         │\n│    ♦    │\n│         │\n│       10│\n└─────────┘";
        assertEquals(expected, actual);
    }

    @Test
    public void printCardImageTest3(){
        Card card = new Card("Clubs", "2");
        String actual=card.printCardImage();
        String expected= "\n┌─────────┐\n│2        │\n│         │\n│    ♣    │\n│         │\n│        2│\n└─────────┘";
        assertEquals(expected, actual);
    }

    @Test
    public void printCardImageTest4(){
        Card card = new Card("Spades", "Ace");
        String actual=card.printCardImage();
        String expected= "\n┌─────────┐\n│A        │\n│         │\n│    ♠    │\n│         │\n│        A│\n└─────────┘";
        assertEquals(expected, actual);
    }

}
