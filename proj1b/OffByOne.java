public class OffByOne implements CharacterComparator {


    @Override
    public boolean equalChars(char x, char y) {
        int difference = Math.abs(y - x);
        if (difference == 1) {
            return true;

        }
        return false;
    }


}
