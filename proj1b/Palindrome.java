public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> temp = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            temp.addLast(word.charAt(i));
        }
        return temp;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> newD = wordToDeque(word);
        String reversed = "";
        for (int i = 0; i < word.length(); i++) {
            reversed = reversed + newD.removeLast();
        }

        if (reversed.equals(word)) {
            return true;
        }
        return false;


    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            for (int i = 0; i < word.length() / 2; i++) {
                boolean compare = cc.equalChars(word.charAt(0 + i),
                        word.charAt((word.length() - 1 - i)));
                if (!compare) {
                    return false;
                }

            }
            return true;



        }

    }
}
