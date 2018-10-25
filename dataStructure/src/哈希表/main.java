package 哈希表;

public class main {
    public static void main(String [] args){
        int a = 99999999;
        System.out.println(((Integer)a).hashCode());
        String str = "abc";
        System.out.println(str.hashCode());
        Student s1 = new Student(1,"abc","CN");
        Student s2 = new Student(1,"abc","CN");
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

    }

}
