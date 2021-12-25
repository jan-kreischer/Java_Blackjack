/*
Returns a card from the deck (=source).
@return The next available card.
@pre !isEmpty()

 */
public interface CardSource {

    //define how to draw a card
    Card drawCard();

    //@return True if there is no card in the deck
    boolean isEmpty();
}
