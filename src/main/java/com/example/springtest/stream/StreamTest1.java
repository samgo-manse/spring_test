package com.example.springtest.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest1 {

    /*
     * 숫자 배열을 짝수에 대해서만 중복은 제거하고 역방향 정렬로 처리
     */
    public void test1() {

        int[] data = {5, 6, 4, 2, 3, 1, 1, 2, 2, 4, 8};

        int[] result = Arrays.stream(data)
                .boxed()  //TODO 왜 필요한걸까?
                .filter(num->num%2==0)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(Arrays.toString(result));

    }

    /*
     * test1과 같고 list를 사용
     */
    public void test3() {

        List<Integer> list = Arrays.asList(Integer.valueOf(5),Integer.valueOf(6),Integer.valueOf(4),Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(1),Integer.valueOf(1),Integer.valueOf(2),Integer.valueOf(2),Integer.valueOf(4),Integer.valueOf(8));

        List<Integer> result = list.stream()
                                .filter(num->num%2==0)
                                .distinct()
                                .sorted(Comparator.reverseOrder())
                                .collect(Collectors.toList()); //toList 바로 사용 가능

        System.out.println(result);

    }

    /*
     * List를 대문자로 변환하여 List로 다시 저장
     */
    public void test2() {

        List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1", "c3");
        List<String> result = list.stream().map(String::toUpperCase).toList();

        System.out.println(result);

    }

    public static void main(String[] args){
        StreamTest1 test = new StreamTest1();
        test.test1();
        System.out.println("===========================");
        test.test2();
        System.out.println("===========================");
        test.test3();

    }
    
}
