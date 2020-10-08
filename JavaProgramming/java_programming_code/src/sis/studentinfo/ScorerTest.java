package sis.studentinfo;

import junit.framework.TestCase;

public class ScorerTest extends TestCase {
    public void testCaptureScore() {
        Scorer scorer = new Scorer();
        assertEquals(75, scorer.score("75"));
    }

    public void testBadScoreEntered() {
        Scorer scorer = new Scorer();
        try {
            scorer.score("abd");
            fail("expected NumberFormatException on bad input");
        } catch (NumberFormatException success) {
        }
    }

    public void testIsValid() {
        Scorer scorer = new Scorer();
        assertTrue(scorer.isValid("75"));
        assertFalse(scorer.isValid("bd"));
    }
}
