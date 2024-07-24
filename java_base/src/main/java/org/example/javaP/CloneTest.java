package org.example.javaP;


class Address1 implements Cloneable{
    private String name;

    public Address1(String name) {
        this.name = name;
    }

    @Override
    public Address1 clone() {
        try {
            return (Address1) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Persons1 implements Cloneable {
    private Address1 address;

    public Persons1(Address1 address) {
        this.address = address;
    }

    // 省略构造函数、Getter&Setter方法
    @Override
    public Persons1 clone() {
        try {
            Persons1 person = (Persons1) super.clone();
            person.setAddress(person.getAddress().clone());
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Address1 getAddress() {
        return address;
    }

    public void setAddress(Address1 address) {
        this.address = address;
    }
}


public class CloneTest {
    public static void main(String[] args) {
        Persons1 person1 = new Persons1(new Address1("武汉"));
        Persons1 person1Copy = person1.clone();
// true
        System.out.println(person1.getAddress() == person1Copy.getAddress());

    }
}
