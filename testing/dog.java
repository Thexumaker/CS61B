public class dog extends animal {
    public void sniff(int n){
        System.out.println("used dog class");

    }
    public static void main(String[] args) {
        animal a = new animal();
        dog d = new dog();
        a.sniff("hi");
        d.sniff("hi");


    }
}


