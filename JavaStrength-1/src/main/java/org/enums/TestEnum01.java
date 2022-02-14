package org.enums;

enum Gender{
    MALE,FEMALE,NONE;
    Gender(){
        System.out.println("Gender()");
    }
}

public class TestEnum01 {
    public static void main(String[] args) {
        Gender male = Gender.MALE;
    }
}
