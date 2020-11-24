package chess;

import junit.framework.TestCase;

public class CharacterTest extends TestCase {
    public void testWhitespace(){
        assertEquals(true, Character.isWhitespace('\n'));
        assertEquals(true, Character.isWhitespace('\t'));
        assertEquals(true, Character.isWhitespace(' '));
        assertEquals(false, Character.isJavaIdentifierPart('^'));

    }
}
