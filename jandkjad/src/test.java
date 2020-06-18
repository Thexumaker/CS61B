import java.util.ArrayList;

public class test {
    public static void reverse(int[] arr) {

        for (int start = 0; start < arr.length / 2; start++) {
            int end = arr.length - start - 1;
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;


        }
    }

    static int findMin(int arr[], int low, int high) {
        if (arr[high] > arr[low]) {
            return arr[low];
        }

    }

    public static int[] replicate(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        int counter = 0;
        int[] result = new int[total];
        for (int item : arr) {

            for (int i = 0; i < item; i++) {
                result[counter] = item;
                counter++;
            }
        }
        return result;


    }

    public static void select(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;

                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }


    }

    public static void insert(int[] arr) {
        // have 1 pointer like the i
        //then have a second pointer and then compare to stuff before
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int target = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > target) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = target;
        }


    }
    public int[] merge(int[] arr) {
        int n = arr.length;
        if ( n <= 1) {
            return arr;
        }
        int[] a = new int[arr.length/2];
        int[] b = new int[arr.length - arr.length/2];
        for (int i = 0; i < a.length;i++) {
            a[i] = arr[i];

        }
        for (int i = 0; i < b.length;i++) {
            b[i] = arr[i];

        }
        return sort(merge(a), merge(b));




    }
    public int[] sort(int[] a, int[]b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        for(int k = 0; k < result.length; k++) {
            if( i > a.length) {
                result[k] = b[j];
                j++;
            }
            else if( j > b.length) {
                result[k] = a[i];
                i++;
            }
            else if (a[i] > b[j]) {
                result[k] = b[j];
                j++;


            }
            else {
                result[k] = a[i];
                i++;
            }

        }
        return result;

    }
    void inorder(Node node) {
        search(node.left)
                print(node.value)
                        search(node,right)
}               ArrayList<Integer> treeToList(Node node, ArrayList<Integer>
            list)
            if (node == null) {
            return list;
        treeToList(node.left, list);
        list.add(node.data);
        treeToList(node.right, list);


        boolean isPairPresent(Node node, int target)
        {
            ArrayList<Integer> a1 = new ArrayList<>();
            ArrayList<Integer> a2 = treeToList(node,a1 );
            int start = 0;
            int end = a2.size()-1;
            while ( start < end) {
                if a1.get(start) + a2.get(end) == target
            done}
            else if (sum < target) {
                start++

                        else end--;
        }

        }


        }
}
    {

        String str = String.valueOf(x);
    int j = str.length() - 1;
        for(int i = 0; i < (str.length()/2) ;i++){
        if(str.charAt(i) != str.charAt(j)) {
        return false;
        }
        j--;

        }
        return true;

        }