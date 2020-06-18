import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void isPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("cac"));
        assertFalse(palindrome.isPalindrome("nibb"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("NIn"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertFalse(palindrome.isPalindrome("aba", offByOne));
        assertFalse(palindrome.isPalindrome("abaA", offByOne));
        assertFalse(palindrome.isPalindrome("Abaa", offByOne));
        assertTrue(palindrome.isPalindrome("AbaB", offByOne));
        assertFalse(palindrome.isPalindrome("AbAbA", offByOne));
        assertTrue(palindrome.isPalindrome("A", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome("Anutforajaroftuna", offByOne));





    }
}
