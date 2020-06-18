/** If you project is set up properly, this file should execute. 
* One thing you might consider is to try printing out the sequence of
* operations */
public class StudentArrayDequeLauncher {
    public static void main(String[] args) {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                correct.addLast(i);
            } else {
                sad1.addFirst(i);
                correct.addFirst(i);
            }
        }
        while(sad1.isEmpty() == false){
            Integer A = sad1.removeLast();
            Integer B = correct.removeLast();
            System.out.println(A + "hi");
            System.out.println(B);
        }




    }
} 
