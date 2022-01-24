package ru.b00blik.jpeanuts.maps;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {

    public static void main(String[] args){

        TreeMap<Person, Integer> treeMap = new TreeMap<>(Comparator.comparingInt(p -> p.age));

        treeMap.put(new Person("Petr", "Ivanov", 35),0);
        treeMap.put(new Person("Ivan", "Petrov", 23),0);
        treeMap.put(new Person("Steve", "Vladimirov", 44),0);
        treeMap.put(new Person("Andy", "Scully", 75),0);
        treeMap.put(new Person("Andy", "Scully", 15),0);

        Person firstAdultPerson = treeMap
                .navigableKeySet()
                .stream()
                .filter(p -> p.age > 18)
                .findFirst()
                .get();

        System.out.println("First young person is " + firstAdultPerson.firstName);

        Map<Person, Integer> adultsMap = treeMap.headMap(firstAdultPerson, false);
        Map<Person, Integer> youngersMap = treeMap.tailMap(firstAdultPerson, true);

        System.out.println("Adults map size: " + adultsMap.size());
        System.out.println("Youngs map size: " + youngersMap.size());
    }

}

class Person {
    public String firstName;
    public String lastName;
    public int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}