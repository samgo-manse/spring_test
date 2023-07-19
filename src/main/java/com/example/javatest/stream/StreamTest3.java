package com.example.javatest.stream;

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

    /* 이중 배열에 짝수가 있으면 필터링 */
    void test6(){
         String[][] namesArray = new String[][] {
                {"kim", "hyoz", "lee"}, {"lee", "sooyoung"},
                {"joy", "taehui"}, {"woo", "park"}
        };
        Arrays.stream(namesArray)
                .flatMap(innerArray -> Arrays.stream(innerArray))
                .filter(name -> name.length() <= 3 )
                //.collect(Collectors.toList())
                .forEach(System.out::println);

    }

    public static void main(final String[] args) throws Exception {
        StreamTest3 stream = new StreamTest3();
        System.out.println("===========================================");
        stream.test4();
        System.out.println("===========================================");
        //stream.test5();
        System.out.println("===========================================");
        stream.test6();
        System.out.println("===========================================");
    }
    
}
