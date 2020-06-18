import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    @Test
    public void equalChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('c', 'a'));
        assertFalse(offByOne.equalChars('c', 'a'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('&', '_'));
        assertFalse(offByOne.equalChars('&', '*'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('A', 'A'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('c', 'c'));




    }


}

