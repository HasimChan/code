package com.hasim.testother.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

public class Collectionexer {
    public static void main(String[] args) {
        Dog dog1 = new Dog("a", 1);
        Dog dog2 = new Dog("b", 2);
        Dog dog3 = new Dog("c", 3);

        ArrayList<Dog> dogs = new ArrayList<Dog>();
        dogs.add(dog1);
        dogs.add(dog2);
        dogs.add(dog3);

        Iterator<Dog> iterator = dogs.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (Dog dog : dogs) {
            System.out.println(dog);
        }

    }
}

class Dog {
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
