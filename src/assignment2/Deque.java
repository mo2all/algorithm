package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description 使用链表实现双向队列
 * @Author Skye
 * @Date 2018/11/27 19:13
 * @Version 1.0
 **/
public class Deque<Item> implements Iterable<Item>  {

    //定义链表节点的结构
    private class Node<Item>{
        Item item;
        Node<Item> next;
        Node<Item> pre;
    }
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }
    // return the number of items on the deque
    public int size() {
        return size;
    }
    // add the item to the front
    public void addFirst(Item item) {
        checkNull(item);
        //将first保存在oldFirst
        Node<Item> oldFirst = first;
        //创建新的节点，并且设置内容，将节点的引用指向下一个oldFirst
        first = new Node<>();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }else   {
            first.next = oldFirst;
            oldFirst.pre = first;
        }
        size++;
    }
    // add the item to the end
    public void addLast(Item item) {
        checkNull(item);
        //将last保存在oldLast中
        Node<Item> oldLast = last;
        //创建新的Node将item保存在last中，并将next指向oldLast
        last = new Node<>();
        last.item = item;
        if (isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
            last.pre = oldLast;
        }
        size++;
    }
    // remove and return the item from the front
    public Item removeFirst() {
        checkEmpty();
        //移除头部节点，并将对象游离
        Node<Item> itemNode = first;
        first = first.next;
        if (size != 1) {
            first.pre = null;
            itemNode.next = null;
        }
        size --;
        return itemNode.item;
    }
    // remove and return the item from the end
    public Item removeLast() {
        checkEmpty();
        //移除节点，并将itemNode设为游离
        Node<Item> itemNode = last;
        last = last.pre;
        if (size != 1) {
            itemNode.pre = null;
            last.next = null;
        }
        size--;
        return itemNode.item;
    }

    // return an iterator over items in order from front to end
    //实现迭代器，使数据结构可迭代
    @Override
    public Iterator<Item> iterator() {
        return  new Iterator<Item>() {
            Node<Item> current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item= current.item;
                current = current.next;
                return item;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    private void checkNull(Item item){
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }
    private void checkEmpty(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }
    // unit testing (optional)
    public static void main(String[] args) {

        Deque<String> stringNode = new Deque<>();
        stringNode.addFirst("i");
        stringNode.addFirst("love");
        stringNode.addFirst("science");
        System.out.println(stringNode.removeFirst());
        System.out.println(stringNode.removeFirst());
        System.out.println(stringNode.removeFirst());
//        for (String string : stringNode
//             ) {
//            System.out.println(string);
//        }
    }







}
