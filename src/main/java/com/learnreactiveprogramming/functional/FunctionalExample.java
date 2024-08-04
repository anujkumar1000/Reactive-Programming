package com.learnreactiveprogramming.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {


    public static void main(String[] args) {
        var namesList = List.of("alex", "ben", "chloe", "adam");
        var newNamesList = nameGreaterThanSize(namesList, 3);
        System.out.println("newNamesList : " + newNamesList);
    }

    private static List<String> nameGreaterThanSize(List<String> namesList, int size) {
        return namesList
                .parallelStream()
                .filter(s -> s.length() > 3)
                .distinct()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    }
