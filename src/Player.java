package com.group17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player implements Person,Observable{

    //class instances
    public ArrayList<Card> cardsOnTable;
    public int cardValue;
    public int money;

    //constructor
    public Player(){
    cardValue = 0;
    money = 100;
    cardsOnTable = new ArrayList<>();
    }

    //define com.group17.Observer methods
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o){observers.add(o);}

    public void removeObserver(Observer o){this.observers.remove(o);}

    public void notifyObserver(){
        for (Observer o: observers) {
            o.printTable(false);
        }
    }

    // returns best value considering aces. If it returns 0, then it is a bust.
    public int checkForAces(){
        int nrAces = 0;
        ArrayList<Integer> indices = new ArrayList<>();
        //count how many Aces
        for (Card c: cardsOnTable){
            if (c.isAce()){
                nrAces += 1;
            }
        }
        // calculate all possible values considering Aces
        ArrayList<Integer> values = new ArrayList<>();
        values.add(cardValue);
        for (int i = 1 ; i<= nrAces ;i++){
            int j = cardValue + (i *10) ;
            values.add(j);
        }

        //evaluate the highest value below 21 from values
        int best = 0 ;
        for (int v : values){
            if (v >= best && v <= 21){
                best = v;
            }
        }
        return best;
    }
/*
    public String printCards(){
        if( cardsOnTable.isEmpty()){
            return "|| No cards ||";
        }
        else {
            String printable= "";
            for (Card c : cardsOnTable) {
                printable += "|| " + c.printSingleCardString() + " " + " ";
            }
            printable += "|| ";
            return printable;

        }
    }
*/

    public String printCardsImage(){
        //com.group17.Card firstCard = cardsOnTable.get(0);
        //System.out.println("|| " + firstCard.getCard() + " of " + firstCard.getColor() + " ||");
        if(cardsOnTable.isEmpty()){
            return "|| No cards ||";
        }
        else {
            String printable = "";

            for (Card c : cardsOnTable) {

                printable += c.printCardImage();
            }

            printable += "";
            return printInLine(printable);

        }

    }

    public  String printInLine (String rowCards){
        // One "┌─────────┐" is 11 characters long, one cards is made of 7 lines

        String printable = "";
        String[] singularParts = rowCards.split("\n");

        singularParts = Arrays.copyOfRange(singularParts, 1, singularParts.length); // Remove first ""- Element

        for (int i = 0; i<7 ; i++){
            for(int j = 0; j<cardsOnTable.size(); j++){
                printable += singularParts[i+7*j];
            }
            printable += "\n";
        }
        return printable;
    }


    public void receiveCard(Card currentCard, boolean verbosity){
        cardValue += currentCard.getValue();
        cardsOnTable.add(currentCard);
        if (verbosity){
            notifyObserver();
        }
    }
    public void resetCurrentCards(){
        cardsOnTable.clear();
        cardValue=0;
    }
    public boolean checkEnoughMoney(int moneyBet){
        if (moneyBet <= money && money > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public void updateMoney(int currentBet){
        money += currentBet ;
    }

}

