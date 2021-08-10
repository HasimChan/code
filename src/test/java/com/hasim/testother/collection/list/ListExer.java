package com.hasim.testother.collection.list;

import java.util.*;

public class ListExer {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//
//        for (int i = 0; i < 11; i++) {
//            list.add("hello" + i);
//        }
//
//        list.add(1, "hasim");
//
//        list.remove(5);
//
//        list.set(6, "set");
//
//        Iterator<String> iterator = list.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        Random random = new Random();

        List<Book> books = new ArrayList<Book>();

        for (int i = 0; i < 10; i++) {
            books.add(new Book("book" + i, random.nextInt(100), "hasim" + i));
        }

        System.out.println(new ListExer().sort(books));
    }

    public List<Book> sort(List<Book> list) {

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getPrice() > list.get(j + 1).getPrice()) {
                    Book temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        return list;
    }
}

class Book {
    private String name;
    private int price;
    private String autor;

    public Book(String name, int price, String autor) {
        this.name = name;
        this.price = price;
        this.autor = autor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", autor='" + autor + '\'' +
                '}' + "\n";
    }
}
