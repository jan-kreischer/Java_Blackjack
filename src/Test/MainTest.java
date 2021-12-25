package com.group17.Test;
import com.group17.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testChristmasTree(){
        assertEquals(Main.printChristmasTree(), "          *\n         ***\n        *****\n       *******\n      *********\n     ***********\n    *************\n   ***************\n  *****************\n *******************\n\nThis is the CHRISTMAS SPECIAL!\nWe wish you MERRY CHRISTMAS and a lot of fun with our Blackjack :)");

    }
}
