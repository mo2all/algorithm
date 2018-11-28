package assignment2;

import edu.princeton.cs.algs4.StdIn;

/**
 * @Description 从命令行中获取K个参数的序列，并将其打印出来，最多为N个，即输入的总数（K<=N）
 * @Author Skye
 * @Date 2018/11/28 14:43
 * @Version 1.0
 **/
public class Permutation {

    public static void main(String[] args) {
        if (args.length != 0){
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
            int k = Integer.valueOf(args[0]);

            while (!StdIn.isEmpty()) {
                String input = StdIn.readString();
                randomizedQueue.enqueue(input);
            }

            for (String str : randomizedQueue
            ) {
                if (k<=0){
                    break;
                }
                System.out.println(str);
                k--;
            }

        }

    }
}
