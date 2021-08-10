package com.hasim.testother.collection.set;


import java.util.*;

public class TestHashSet {
    public static void main(String[] args) {
        Employee hasim1 = new Employee("hasim", new MyDate(new Date(2020)));
        Employee hasim2 = new Employee("hasim", new MyDate(new Date(2020)));

        HashSet<Employee> employees = new HashSet<>();

        employees.add(hasim1);
        System.out.println(employees);
        employees.add(hasim2);
        System.out.println(employees);

    }

}

class Employee {
    private String name;
    private MyDate birthday;

    public Employee(String name, MyDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(birthday, employee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

class MyDate {
    private Date date;

    public MyDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return Objects.equals(date, myDate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "date=" + date +
                '}';
    }
}
