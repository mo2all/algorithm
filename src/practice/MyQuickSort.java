package practice;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


/**
 * @Description 实现快排
 * @Author Skye
 * @Date 2018/11/30 8:52
 * @Version 1.0
 **/
public class MyQuickSort{

    /**
     * 交换两个元素
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[]a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 如果元素a比元素b小，则返回true
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void sort(Comparable[] a){
        int n = a.length;
        shuffle(a);
        sort(a, 0, n-1);

    }
    private static void sort(Comparable[]a, int lo, int hi){
        if (lo >= hi) return;
        int partition = partition(a, lo, hi);
        sort(a, lo, partition -1);
        sort(a, partition+1, hi);
    }

    /**
     * 快速排序的主要部分（递归的通用部分），对数组进行随机切割，并且在该位置的左边全部小于或等于该元素，右边全部大于或等于
     * @param a
     * @param lo
     * @param hi
     * @return 返回元素的切片位
     */
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi+1;
        while (true){
            while (less(a[++i], a[lo] ))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if(j == lo) break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void shuffle(Comparable[] a){
        int n = a.length;
        for (int i = 0; i < n; i ++) {
            int r = i + StdRandom.uniform(n-i);//产生i~n-1的随机数
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        Student[] students = new Student[6];
        Student s1 = new Student("Skye", 20);
        Student s2 = new Student("taller Skye", 25);
        Student s3 = new Student(" thin Skye", 18);
        Student s4 = new Student(" young Skye", 16);
        Student s5 = new Student("steady Skye", 21);
        students[0] =s1;
        students[1] =s2;
        students[2] =s3;
        students[3] =s4;
        students[4] =s5;
        students[5] =s5;
        System.out.println("====快速排序前====");
        System.out.println(Arrays.toString(students));

        System.out.println("====快速排序后====");
        sort(students);
        System.out.println(Arrays.toString(students));

    }

}
