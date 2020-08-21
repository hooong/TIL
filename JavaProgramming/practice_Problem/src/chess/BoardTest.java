package chess;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
    public void testCreate() {
        Board board = new Board();

        board.addPiece();
        assertEquals(board.getCountOfPieces(), 1);
    }
}
