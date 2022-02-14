package org.enums;

enum Sex{
    MAILE("男"),FEMALE("女");//执行有参的构造函数
    private String name;


    Sex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Member{
    Long id;
    Sex sex = Sex.MAILE;
}
public class TestEnum02 {
    public static void main(String[] args) {
        Member m = new Member();
        System.out.println(m.sex.getName());
    }
}
