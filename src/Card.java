import java.util.ArrayList;
import java.util.Arrays;

public class Card{
    private String color; //"Hearts", "Diamonds", "Clubs", "Spades"
    private String typ; //"2",..., "10", "Jack",...,"Ace"
    //suits_symbols = ['♠', '♦', '♥', '♣']
    // lines = [['┌─────────┐'], ['│░░░░░░░░░│'], ['│░░░░░░░░░│'], ['│░░░░░░░░░│'], ['│░░░░░░░░░│'], ['│░░░░░░░░░│'], ['│░░░░░░░░░│'], ['│░░░░░░░░░│'], ['└─────────┘']]

    public Card(String c, String t){
        color = c;
        typ = t;

    }

    private String getSymbol(){
        String symbol;
        if (this.color == "Hearts"){
            symbol= "♥";
        } else if (this.color == "Diamonds"){
            symbol="♦";
        } else if (this.color == "Clubs"){
            symbol="♣";
        }else {
            symbol="♠";
        }
        return symbol;
    }

    /*//get color from com.group17.Card
    public String getColor(){
        return this.color;
    }

    //get typ of com.group17.Card
    public String getCard(){
        return this.typ;
    }*/

    //get value of the com.group17.Card
    public int getValue(){
        if (this.typ =="Ace"){
            return 1;
        } else if (Arrays.asList("10", "Jack", "Queen", "King").contains(this.typ)){
            return 10;
        } else
            return Integer.parseInt(this.typ);
    }

    public String printSingleCardString(){
        String card;
        card =  this.typ + " of " + this.color;
        return card;

    }


    public boolean isAce(){
        if (this.typ == "Ace"){
            return true;
        } else{
            return false;
        }
    }

    public String printCardUpSideDown(){
        String[][] cardUpSideDown={{"┌─────────┐"}, {"│░░░░░░░░░│"}, {"│░░░░░░░░░│"}, {"│░░░░░░░░░│"}, {"│░░░░░░░░░│"}, {"│░░░░░░░░░│"}, {"└─────────┘"}};
        String cardAsString ="\n┌─────────┐\n";
        for (int i=1;i<6;i++){
            cardAsString +=cardUpSideDown[i][0]+"\n";
            //System.out.printf(cardUpSideDown[i][0]+"\n");
        }
        cardAsString += cardUpSideDown[6][0];
        return cardAsString;

    }

    public String printCardImage(){
        String symbol = "│    " + this.getSymbol() + "    │";
        String number1;
        String number2;

        if (this.typ == "10") {
            number1 = "│10       │";;
            number2 = "│       10│";
        }else if(Arrays.asList("2","3","4","5","6","7","8","9").contains(this.typ)) {
            number1= "│"+this.typ+"        │";
            number2= "│        "+this.typ+"│";
        }else {
            String fristLetter =String.valueOf(this.typ.charAt(0));
            number1="│"+fristLetter+"        │";
            number2="│        "+fristLetter+"│";
        }

        String[][] card = {{"┌─────────┐"}, {number1}, {"│         │"}, {symbol}, {"│         │"}, {number2}, {"└─────────┘"}};
        String cardAsString ="\n┌─────────┐\n";
        for (int i=1;i<6;i++){
            cardAsString +=card[i][0]+"\n";
            //System.out.printf(card[i][0]+"\n");
        }
        cardAsString +=card[6][0];
        return cardAsString;

    }

}

