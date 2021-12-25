import com.group17.*;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.DeclaredType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class    DeckTest {

    //deck has no next card
    @Test
    public void hasNextTest1(){
        Deck deck = new Deck();
        for (int i=0;i<52;i++){
            deck.drawCard();//deck.drawCard(false);
        }
        boolean actual = deck.hasNext();
        boolean expected = false;
        assertEquals(expected,actual );

    }

    //deck has next card
    @Test
    public void hasNextTest2(){
        Deck deck = new Deck();
        boolean actual = deck.hasNext();
        boolean expected = true;
        assertEquals(expected,actual );

    }

    //get next card and draw card
    @Test
    public void nextAndDrawCardTest(){
        Deck deck = new Deck();
        Card actual = deck.next();
        Card expected = deck.drawCard();//Card expected = deck.drawCard(true);
        assertEquals(expected,actual );

    }

    //deck is empty
    @Test
    public void isEmptyTest1(){
        Deck deck = new Deck();
        for (int i=0;i<52;i++){
            deck.drawCard();//deck.drawCard(false);
        }
        boolean actual = deck.isEmpty();
        boolean expected = true;
        assertEquals(expected,actual);

    }

    //deck is not empty
    @Test
    public void isEmptyTest2(){
        Deck deck = new Deck();
        boolean actual = deck.isEmpty();
        boolean expected = false;
        assertEquals(expected,actual);

    }

}
