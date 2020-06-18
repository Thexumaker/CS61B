import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
    String rf = " ";


    @Test
    public void basicTest(){

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                correct.addLast(i);
                rf = rf + "addLast(" + i + ")" + "\n";
                Integer a = correct.get(correct.size()-1);
                Integer b = sad1.get(sad1.size()-1);
                assertEquals(rf  , a, b);
            }
            else if( numberBetweenZeroAndOne >= .25 && numberBetweenZeroAndOne < .5) {
                sad1.addFirst(i);
                correct.addFirst(i);
                rf = rf + "addFirst(" + i + ")" + "\n";
                Integer a = correct.get(0);
                Integer b = sad1.get(0);
                assertEquals(rf  , a, b);

            }
            else if(numberBetweenZeroAndOne >= .5 && numberBetweenZeroAndOne < .75) {
                if(sad1.isEmpty() == false && correct.isEmpty() == false ) {
                        Integer d = sad1.removeLast();
                        Integer e =correct.removeLast();
                        rf = rf +  "removeLast()"+ "\n";
                        assertEquals(rf  , e, d);

                    }
            }
            else {
                if(sad1.isEmpty() == false && correct.isEmpty() == false ){
                    Integer a = sad1.removeFirst();
                    Integer b = correct.removeFirst();
                    rf = rf + "removeFirst()"+ "\n";
                    assertEquals(rf  , b, a);

                }
            }

        }

    }
}


















