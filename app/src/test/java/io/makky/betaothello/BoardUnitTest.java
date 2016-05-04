package io.makky.betaothello;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardUnitTest {
    @Test
    public void initTest() throws Exception {
        Board board = new Board();

        assertEquals(8, board.getState().length);
    }
}
