package com.example.springtest.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest3 {
    void test4() {
        List<String> strings = Arrays.asList("apple","banana","pear");
        Map<Integer, String> map = strings.stream().collect(Collectors.toMap(String::length, Function.identity()));
        System.out.println(map);
    }

    void test5() {
        List<String> strings = Arrays.asList("apple","banana","carrot","pear");
        Map<Integer, String> map = strings.stream().collect(Collectors.toMap(String::length, Function.identity()));
        System.out.println(map);
    }

    public static void main(final String[] args) throws Exception {
        StreamTest3 stream = new StreamTest3();
        System.out.println("===========================================");
        stream.test4();
        System.out.println("===========================================");
        stream.test5();
        System.out.println("===========================================");
    }
    
}
