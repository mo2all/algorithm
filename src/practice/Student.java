package practice;

/**
 * @Description
 * @Author Skye
 * @Date 2018/11/30 10:01
 * @Version 1.0
 **/
public class Student implements Comparable<Student> {
    private String name;
    private int age;
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Student that) {
        int div = this.age - that.age;
        if (div < 0)
            return -1;
        else if (div > 0)
            return +1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
