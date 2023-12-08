package com.example.javatest.stream;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamTest4 {

    /* 두 개의 필드 합을 한번에 계산 */
    void test1() {
        final var list = new ArrayList<EiEmployeePremiumAmountResponse>();

        list.add(new EiEmployeePremiumAmountResponse(
                null,
                null,
                null,
                null,
                null,
                1000D,
                2000D,
                null,
                null));

        list.add(new EiEmployeePremiumAmountResponse(
                null,
                null,
                null,
                null,
                null,
                1000D,
                2000D,
                null,
                null));

        final var result = list.stream()
                .collect(Collectors.teeing(Collectors.summingDouble(EiEmployeePremiumAmountResponse::monthPremAmount),
                        Collectors.summingDouble(EiEmployeePremiumAmountResponse::yrSettlePremAmount),
                        (monthPremAmount, yrSettlePremAmount) -> new EiEmployeePremiumAmountResponse(
                                null,
                                null,
                                null,
                                null,
                                null,
                                monthPremAmount,
                                yrSettlePremAmount,
                                null,
                                null)));

        System.out.println(result);

    }

    public static void main(final String[] args) throws Exception {
        StreamTest4 stream = new StreamTest4();
        System.out.println("===========================================");
        stream.test1();
    }
}
