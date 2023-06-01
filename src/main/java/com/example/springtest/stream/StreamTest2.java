package com.example.springtest.stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

/*
 * https://mangkyu.tistory.com/116 문제 풀이
 */
public class StreamTest2 {

    /*
     * 1.1 해결중
     */
    public void test1() {

        String[][] list1 = { {"김프로", "축구:농구:야구", "구기종목 좋아요"},
                            {"정프로", "개발:당구:축구", "개발하는데 뛰긴 싫어"},
                            {"앙몬드", "피아노", "죠르디가 좋아요 좋아좋아너무좋아"},
                            {"죠르디", "스포츠댄스:개발", "개발하는 죠르디 좋아"},
                            {"박프로", "골프:야구", "운동이 좋아요"},
                            {"정프로", "개발:축구:농구", "개발도 좋고 운동도 좋아"}
                            };
        
        List<Student> students = new ArrayList<>();

        for( String[] row : list1 ){
            students.add(new Student(row[0],row[1],row[2]));
        }
        // String[] list = {"김프로", "축구:농구:야구", "구기종목 좋아요"};
        // List<String> arrayList = Arrays.asList(list);
        // arrayList.stream().map(str->str.split(":").length).forEach(System.out::println);

        // List<Student> students = Arrays.asList(new Student("김프로","축구:농구:야구","구기종목 좋아요"));
        // int sum = students.stream().mapToInt(student->student.hobby().split(":").length).sum();
        // System.out.println(sum);

        // List<Student> students = Arrays.asList(new Student(list1[0][0],list1[0][1],list1[0][2]),
        //                                         new Student(list1[1][0],list1[1][1],list1[1][2]),
        //                                         new Student(list1[2][0],list1[2][1],list1[2][2]),
        //                                         new Student(list1[3][0],list1[3][1],list1[3][2]),
        //                                         new Student(list1[4][0],list1[4][1],list1[4][2]),
        //                                         new Student(list1[5][0],list1[5][1],list1[5][2]));

        //int sum = students.stream().mapToInt(student->student.hobby().split(":").length).sum();
        //System.out.println(sum);

        //students.stream().flatMap(student->Arrays.asList(student.hobby().split(":")).stream()).forEach(System.out::println);
        Map<String, List<String>> result = students.stream()
                                                    .flatMap(student -> Arrays.asList(student.hobby().split(":"))
                                                                                .stream())
                                                    .collect(Collectors.groupingBy(String::new));
                                                    //.entrySet();
                                                    //.forEach((entry) -> System.out.println());
        result.entrySet().forEach(entry->System.out.println(entry.getKey() + " " + entry.getValue().size()));

        // for(Map.Entry<String, List<String>> entry : result.entrySet()){
        //     System.out.println(entry.getKey() + " " + entry.getValue().size());
        // }
    }

    public void test1_answer() throws IOException, CsvException {
        List<String[]> csvLines = readCSVLines();

        Map<String, Integer> result = csvLines.stream()
                .map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue));

        // Map<Object, Object> result = csvLines.stream()
        //                                 .map(line -> line[1].replaceAll("\\s", ""))
        //                                 .map(hobbies -> Arrays.stream(hobbies.split(":")))
        //                                 .collect(Collectors.toMap(hobby->hobby,hobby -> 1, (oldvalue,newvalue) -> newvalue += oldvalue));
    }

    public static void main(final String[] args){
        StreamTest2 stream = new StreamTest2();
        System.out.println("===========================================");
        stream.test1();
        System.out.println("===========================================");
    }

    private List<String[]> readCSVLines() throws IOException, CsvException {
        CSVReader csv = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csv.readNext();
        return csv.readAll();
    }
    
}
