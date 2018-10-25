package 哈希表;

public class Student {
    private int age;
    private String name;
    private String address;

    public Student(int age, String name, String address){
        this.age = age;
        this.name = name;
        this.address = address;
    }

    @Override
    public int hashCode(){
        int temp = 31;
        int hash = 0;
        hash += temp * age;
        hash += temp * this.name.hashCode();
        hash += temp* this.address.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Student auOther = (Student)o;
        return this.age == auOther.age && this.name.equals(auOther.name) && this.address.equals(auOther.address);
    }

}







