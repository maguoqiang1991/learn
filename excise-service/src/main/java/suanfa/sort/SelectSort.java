package suanfa.sort;

/**
 * @author maguoqiang
 * @date 2021-07-12 12:36
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] a={};
        int n=10;
        for (int i = 0; i < n; i++) {
            int minloc=i;
            for (int j=i+1;j<n-1;j++){
                if (a[j]<a[i]){
                    minloc = j;
                }
            }
            swap(a[i],a[minloc]);
        }
    }

    private static void swap(int i, int i1) {

    }
}
