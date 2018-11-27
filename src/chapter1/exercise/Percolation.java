package chapter1.exercise;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * @Description 渗滤系统，row col中对应的数据为uf(col-1+n(row-1))
 * @Author Skye
 * @Date 2018/11/26 11:12
 * @Version 1.0
 **/
public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int n;
    private static final  int topRow = 1;
    private final  int bottomRow;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private static boolean[] status; // 状态数组，0表示不开放，1表示开放


    /**
     * 构造一个n*n的网格，数组下标为0->n-1
     * n*n 表示virtual top site n*n+1表示virtual bottom site
     * @param n
     */
    public Percolation(int n) {
        this.n = n;
        bottomRow = n;
        uf = new WeightedQuickUnionUF(n*n+2);
        status = new boolean[n*n+2];
        virtualTopSite = n*n;
        virtualBottomSite = n*n+1;
    }

    /**
     * 连接前后左右的开放的点
     *open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        checkRange(row,col);
        int location = location(row,col);
        status[location] = true;
        //row-1>0 &&row +1 < n && col-1>0 &&col+1<n
        //正上方
        if (row != topRow && (isOpen(row-1,col))) {
            uf.union(location, location - n);
        }else if (row == topRow){
            uf.union(location, virtualTopSite);
        }
        //正下方打开则连接
        if (row != bottomRow && (isOpen(row+1,col))) {
            uf.union(location, location + n);
        }else if (row == bottomRow){
            uf.union(location, virtualBottomSite);
        }

        //左边打开则连接
        if (col-1>0 && (isOpen(row,col-1)))
            uf.union(location, location-1);
        //右边打开则连接
        if (col+1 <= n &&(isOpen(row,col+1)))
            uf.union(location, location+1);
    }

    /**
     *is site (row, col) open?
     * @return
     */
    public boolean isOpen(int row, int col) {
        checkRange(row,col);
        return status[location(row, col)];
    }

    /**
     *is site (row, col) full?
     * 测试是否可以与top可达
     * @return
     */
    public boolean isFull(int row, int col) {
        checkRange(row,col);
        return uf.connected(virtualTopSite, location(row, col));
    }

    /**
     *number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < n*n; i++) {
           if ( status[i]){
               count += 1;
           }
        }
        return count;
    }

    /**
     *does the system percolate?
     * 如果是可渗滤的则返回true
     * @return
     */
    public boolean percolates() {
        //最后一个表示下虚拟点
        return uf.connected(virtualTopSite,virtualBottomSite);
    }
    private int location(int row, int col) {
        return col-1+n*(row-1);
    }
    private void checkRange(int row, int col){
        if (row <=0 && col <= 0 && row >n && col > n){
            throw new IllegalArgumentException();
        }
    }
//    public static void main(String[] args){
//
//    }

}
