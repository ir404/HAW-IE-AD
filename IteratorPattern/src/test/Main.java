package test;

import datastructures.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new List<>();
        names.add("John");
        names.add("Moses");
        names.add("Sara");
        names.add("Emma");

        names.print();
        System.out.println("Length: " + names.length());

        System.out.println(names.get(1));

        names.remove("Sara");
        names.remove("John");
        names.print();
        System.out.println("Length: " + names.length());

        System.out.println(names.get(1));

        names.remove("Moses");
        names.print();

        names.clear();
        names.print();
    }
}
