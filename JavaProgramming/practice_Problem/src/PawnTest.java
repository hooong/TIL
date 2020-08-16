import junit.framework.TestCase;

public class PawnTest extends TestCase {
    public void testCreate() {

        // Pawn 객체를 생성하는 쪽에서 색을 전달하도록 한 기존 생성자는 없앤다?

        final String baseColor = "white";
        final String oppositeColor = "black";

        Pawn firstPawn = new Pawn(baseColor);
        assertEquals(baseColor, firstPawn.getColor());

        Pawn secondPawn = new Pawn(oppositeColor);
        assertEquals(oppositeColor, secondPawn.getColor());
    }
}