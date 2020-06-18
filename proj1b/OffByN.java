public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int N) {
        this.diff = N;


    }

    @Override
    public boolean equalChars(char x, char y) {
        int diffs = Math.abs(x - y);
        if (this.diff != diffs) {
            return false;
        }
        return true;



    }




}
