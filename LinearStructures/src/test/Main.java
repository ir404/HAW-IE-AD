package test;

import data.Card;
import data.Person;
import datastructures.Deque;
import datastructures.PriorityQueue;
import datastructures.Queue;
import datastructures.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------- STACK ------------------");
        testStack();

        System.out.println("\n\n--------- QUEUE ------------------");
        testQueue();

        System.out.println("\n\n--------- DEQUE (of cards) ------------------");
        testDeque();

        System.out.println("\n\n--------- PRIORITY QUEUE ------------------");
        testPriorityQueue();
    }

    public static void testStack() {
        Stack<String> animals = new Stack<>();
        animals.push("ant");
        animals.push("bug");
        animals.push("cat");
        animals.push("deer");
        System.out.println("Top of stack: " + animals.top());
        animals.print();

        String p = animals.pop();
        System.out.println("Popped: " + p);
        animals.print();

        p = animals.pop();
        System.out.println("Popped: " + p);
        animals.print();

    }

    public static void testQueue() {
        Queue<Person> lineToWatchTheGame = new Queue<>();
        lineToWatchTheGame.enqueue(new Person("James Potter", 27, 'M'));
        lineToWatchTheGame.enqueue(new Person("Lily Potter", 27, 'F'));
        lineToWatchTheGame.enqueue(new Person("Harry Potter", 16, 'M'));
        lineToWatchTheGame.enqueue(new Person("Ron Weasley", 17, 'M'));

        System.out.println("Front of queue: " + lineToWatchTheGame.watch());
        lineToWatchTheGame.print();

        Person p = lineToWatchTheGame.dequeue();
        System.out.println("\nDequeued: " + p.getName());
        lineToWatchTheGame.print();

        System.out.println("\nLength: " + lineToWatchTheGame.length());
    }

    public static void testDeque() {
        Deque<Card> cards = new Deque<>();
        cards.addTop(new Card("Spades", "Queen"));
        cards.addTop(new Card("Spades", "King"));
        cards.addBottom(new Card("Spades", "Jack"));
        cards.addBottom(new Card("Spades", "10"));
        cards.addBottom(new Card("Spades", "9"));
        cards.addTop(new Card("Spades", "Ace"));
        cards.print();

        Card r = cards.removeTop();
        System.out.println("\nRemoved: " + r);
        cards.print();

        r = cards.removeBottom();
        System.out.println("\nRemoved: " + r);
        cards.print();
    }

    public static void testPriorityQueue() {
        PriorityQueue<Person> management = new PriorityQueue<>();
        management.enqueue(new Person("CFO Frank", 42, 'M'), 2);
        management.enqueue(new Person("HoD Benjamin", 38, 'M'), 3);
        management.enqueue(new Person("CEO Edmund", 44, 'M'), 1);
        management.enqueue(new Person("COO Olivia", 40, 'F'), 2);
        management.enqueue(new Person("HoD Denise", 36, 'F'), 3);

        management.print();
        management.dequeue();
        management.dequeue();
        management.print();
    }
}
