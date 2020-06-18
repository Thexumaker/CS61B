import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static class Node {
        private boolean isKey;
        char ch;
        HashMap<Character, Node> children = new HashMap<Character, Node>();
        private Node() {

        }
        private Node(char c, boolean b) {
            ch = c;
            isKey = b;
            children = new HashMap<Character, Node>();
        }
    }
    private Node root;
    public MyTrieSet() {
        root = new Node();
    }
    @Override
    public void clear() {
        Node curr = root;
        root.children.clear();
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }

        if (contains(key)) {
            return;
        }
        Node curr = root;

        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node(c, false));
            }
            curr = curr.children.get(c);
        }
        curr.isKey = true;
    }
    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        String result = "";
        int length = key.length();


        Node temp = root;

        int level;
        for (level = 0; level < length; level++) {
            // Find current character of str
            char ch = key.charAt(level);

            HashMap<Character, Node> child = temp.children;

            if (child.containsKey(ch)) {
                result += ch;
                temp = child.get(ch);
            } else {
                break;
            }
        }
        if (result.equals(key)) {
            return true;
        }
        return false;



    }
    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("argument to keyswithprefix is null");
        }
        List<String> finals = new ArrayList<>();


        Node curr = root;
        int length = prefix.length();
        for (int level = 0; level < length; level++) {
            char ch = prefix.charAt(level);
            HashMap<Character, Node> child = curr.children;

            if (child.containsKey(ch)) {

                curr = child.get(ch);
            } else {

                break;
            }
        }
        for (Character c : curr.children.keySet()) {
            colHelp(prefix + c, finals, curr.children.get(c));
        }
        return finals;


    }
    private void colHelp(String s, List<String> x, Node n) {

        if (n.isKey) {
            x.add(s);
        }
        for (Character c: n.children.keySet()) {
            colHelp(s + c, x, n.children.get(c));
        }

    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new java.lang.UnsupportedOperationException("oh no mamamamamamama");
    }


}
