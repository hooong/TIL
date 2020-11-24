package chess.pieces;

import junit.framework.TestCase;

public class PawnTest extends TestCase {
    public void testCreate() {
        final String COLOR_WHITE = "p";
        final String COLOR_BLACK = "P";

        Pawn WhitePawn = new Pawn();
        Pawn BlackPawn = new Pawn(COLOR_BLACK);

        assertEquals("p" ,WhitePawn.getColor());
        assertEquals("P" ,BlackPawn.getColor());
    }
}