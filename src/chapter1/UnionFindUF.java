package chapter1;

/**
 * @Description union-find算法，将每个对象当成一颗树
 * @Author Skye
 * @Date 2018/11/26 9:05
 * @Version 1.0
 **/
public class UnionFindUF {
    private int[] id;
    public UnionFindUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++){id[i] = i;}
    }

    private int root(int i){
        while (id[i] != i) i = id[i];
        return i;
    }
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
