package chess;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
    public void testCreate() {
        Board board = new Board();

        assertEquals(board.getCountOfPieces(), 16);


//        StringBuilder initboard = board.initialize();



    }
}
