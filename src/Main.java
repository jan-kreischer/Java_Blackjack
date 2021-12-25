package com.group17;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.sound.sampled.*;

public class Main {

    //creates a game object and starts the game Process

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //special order by Jan
        Sound sound = new Sound();
        sound.playMusic();

        // Print the christmas Tree
        System.out.println(printChristmasTree());

        // Start the game
        Game currentGame = new Game();
        currentGame.gameProcess();

        }

    public static String printChristmasTree(){
        // Create the Christmas tree string
        String tree="";
        for (int i = 0; i < 10; i++) {
            String line="";
            for (int j = 0; j < 10 - i; j++)
                line +=" ";
            for (int k = 0; k < (2 * i + 1); k++)
                line+="*";
            tree+=line+"\n";
        }
        return tree +"\nThis is the CHRISTMAS SPECIAL!\nWe wish you MERRY CHRISTMAS and a lot of fun with our Blackjack :)";
    }

    }
