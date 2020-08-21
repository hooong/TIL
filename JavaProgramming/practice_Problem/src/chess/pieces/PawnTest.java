package chess.pieces;

import junit.framework.TestCase;

public class PawnTest extends TestCase {
    public void testCreate() {
        final String COLOR_WHITE = "White";
        final String COLOR_BLACK = "Black";

        Pawn WhitePawn = new Pawn();
        Pawn BlackPawn = new Pawn(COLOR_BLACK);
    }
}