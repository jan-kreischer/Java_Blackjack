//defines all methods which every person (player and dealer) have in common
public interface Person {

    int checkForAces();

    //String printCards();

    String printCardsImage();

    void receiveCard(Card currentCard, boolean verbosity);

    void resetCurrentCards();


}
