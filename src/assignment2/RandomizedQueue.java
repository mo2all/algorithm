package assignment2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description 随机的移除
 * @Author Skye
 * @Date 2018/11/27 21:29
 * @Version 1.0
 **/
public class RandomizedQueue<Item> implements Iterable<Item>{

    private  Item[] a;
    private  int size;

//    private  int head;
//    private  int tail;


    // construct an empty randomized queue
    public RandomizedQueue(){
        //使用了强制类型转换，创建了泛型数组
        a = (Item[]) new Object[1];
        size = 0;
//        head = 0;
//        tail = 0;
    }
    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }
    // return the number of items on the randomized queue
    public int size(){
        return size;
    }
    // add the item
    public void enqueue(Item item){
        checkNull(item);
        if (size == a.length){
            a = risingArray(a, size*2);
        }
        a[size++] = item;
//        tail++;
    }
    // remove and return a random item
    public Item dequeue(){
        checkEmpty();

        Item item = dequeue(a,size);
        size--;
        if (size > 0 && size == a.length/4){
            risingArray(a, a.length/2);
        }
        return item;

    }
    private Item dequeue(Item items[], int size){

        //随机选中的数将其移除，并使用数组末尾的数对其填充
        int randomInt = StdRandom.uniform(size);
//        int index = randomInt + head;
        Item item = items[randomInt];

        items[randomInt] = items[--size];
//        if ((size+1) != items.length)
            items[size] = null;
        return item;
        //使用二分法对数组中的值进行移动
        /*if (randomInt <= size/2){
            while (index > head){
                a[index] = a[--index];
            }
        }else if (randomInt > size/2){
            while (index < tail){
                a[index] = a[++index];
            }
        }*/

    }
//    private Item dequeueHead(){
//        //取出当前值，即第一个的值
//        Item oldHead = a[head++];
//        a[head-1] = null;
//        return oldHead;
//    }
    // return a random item (but do not remove it)
    public Item sample(){
        checkEmpty();
        int randomInt = StdRandom.uniform(size);
        Item item = a[randomInt];
        return item;
    }
    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        Item[] items =a.clone();
        return new Iterator<Item>() {

            private int sizeOfItems = size;

            @Override
            public boolean hasNext() {
                return sizeOfItems > 0;
            }

            @Override
            public Item next() {
                if (sizeOfItems == 0){
                    throw new NoSuchElementException();
                }

                return dequeue(items, sizeOfItems--);
            }
        };
    }
    private Item[] risingArray(Item[] a, int n){
        Item[] copy =(Item[]) new Object[n];
        if (n > a.length) {
            for (int i = 0; i < a.length; i++) {
                copy[i] = a[i];
            }
        }else if (n < a.length){
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }
        }
        return copy;
    }
    //从start位置开始重新分配,分配大小为n,应为原数组中的有效容量，即为size的大小
    private Item[] risingArray(Item[] a, int n, int start){
        Item[] copy =(Item[]) new Object[n];
        for (int i = 0; i < a.length; i++){
            copy[i] = a[i+start];
        }
        return copy;
    }
    //校验空值
    private void checkNull(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
    }
    //校验队列大小，为空时调用dequeue, sample, next等抛出异常
    private void checkEmpty(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
    }
    // unit testing (optional)
    public static void main(String[] args)  {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("我");
        randomizedQueue.enqueue("是");
        randomizedQueue.enqueue("甘");
        randomizedQueue.enqueue("镇");
        randomizedQueue.enqueue("豪");
        for (String str : randomizedQueue
             ) {
            System.out.print(str+ " ");
        }
        System.out.println("\n");
//        System.out.print("\n"+randomizedQueue.dequeue()+" ");
//        System.out.print(randomizedQueue.dequeue()+" ");
//        System.out.print(randomizedQueue.dequeue()+" ");
//        System.out.print(randomizedQueue.dequeue()+" ");
    }


}
