package com.example.javatest.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * https://tecoble.techcourse.co.kr/post/2021-04-26-defensive-copy-vs-unmodifiable/
 */
public class UnmodifiableTest1 {

    public void test() {
        List<Name> originalNames = new ArrayList<>();
        originalNames.add(new Name("Fafi"));
        originalNames.add(new Name("Kevin"));

        List<Name> crewNames = Collections.unmodifiableList(originalNames); // crewNames: Fafi, Kevin

        originalNames.add(new Name("Sally")); // crewNames: Fafi, Kevin, Sally

        crewNames.add(new Name("daebok")); // crewNames은 unmodifiableList를 통해서 만들어졌기 때문에 변경 불가능함

        System.out.println(originalNames);
        System.out.println(crewNames);
    }

    public static void main(String[] args) {
        UnmodifiableTest1 a = new UnmodifiableTest1();
        a.test();
    }

    public class Name {
        private final String name;

        public Name(String name) {
            this.name = name;
        }
    }

    public class Names {
        private final List<Name> names;

        public Names(List<Name> names) {
            this.names = names;
        }
    }
}
