package io.makky.betaothello;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardUnitTest {
    @Test
    public void initTest() throws Exception {
        GameBoard board = new GameBoard ();

        assertEquals(8, board.getBoard().length);
    }
}
