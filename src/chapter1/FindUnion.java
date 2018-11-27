package chapter1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description 快速联合算法
 * @Author Skye
 * @Date 2018/11/25 22:19
 * @Version 1.0
 **/
public class FindUnion {
    int a[];
    int N;
    public FindUnion(int N){
        this.N = N;
        a = new int[N];
        for (int i = 0; i < N; i ++){
            a[i] = i;
        }
    }
    public void union(int p, int q){
        int pid = a[p];
        int qid = a[q];
        for (int i = 0; i < N; i ++){
            if (a[i] ==pid){
                a[i] = qid;
            }
        }
    }
    public boolean connected(int p, int q){
        return a[p] == a[q];
    }

}
class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int p;
        int q;
        FindUnion findUnion  = new FindUnion(N);
        while (true){
            p= input.nextInt();
            q = input.nextInt();
            if (p == 100 || q==100)
            break;
            findUnion.union(p,q);
        }
        System.out.println(Arrays.toString(findUnion.a));
    }
}
