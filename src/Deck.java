import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck implements Iterable, Shuffable, CardSource {
    private ArrayList<Card> deckOfCards = new ArrayList<>();

    //contractor: initiate the deck of cards and shuffles the deck
    public Deck(){
        //initialise the deck of cards
        List<String> cardTypes = Arrays.asList("Ace", "King", "Queen", "Jack","10", "9","8","7","6","5","4","3","2");
        List<String> cardColors = Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades");
        for (int i=0;i<4; i++){
            for (int j=0; j<13;j++){

                deckOfCards.add(new Card(cardColors.get(i),cardTypes.get(j)));
            }
        }
        shuffle();
    }


    //define methods form interfaces
    public void shuffle(){
        Collections.shuffle(deckOfCards);
    }

    //ask if there is a next object
    public boolean hasNext(){
        if(isEmpty() !=true){
            return true;
        } else {
            return false;
        }
    }

    //ask for the next com.group17.Card
    public Card next(){
        return deckOfCards.get(0);

    }

    //draw the next card and remove card from deck
    public Card drawCard(){
        Card card = this.next();
        //remove card from deck
        deckOfCards.remove(0);
        return card;
    }

    //check if deck is empty
    public boolean isEmpty(){
        if (deckOfCards.size()>0){
            return false;
        } else {
            return true;
        }
    }
    /*
    //define com.group17.Observer methods
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o){observers.add(o);}

    public void removeObserver(Observer o){this.observers.remove(o);}

    public void notifyObserver(){
        for (Observer o: observers) {
            o.printTable(false);
        }
    }
    */
    //reset the whole deck and start from new
    public void resetDeck(){
        //remove rest elements of the deck
        deckOfCards.clear();
        //initialise the deck of cards
        List<String> cardTypes = Arrays.asList("Ace", "King", "Queen", "Jack","10", "9","8","7","6","5","4","3","2");
        List<String> cardColors = Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades");
        for (int i=0;i<4; i++){
            for (int j=0; j<13;j++){
                deckOfCards.add(new Card(cardColors.get(i),cardTypes.get(j)));
            }
        }
        shuffle();
    }
}


