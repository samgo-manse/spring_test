package com.example.javatest.stream;

public record EiEmployeePremiumAmountResponse(
        String employeeNumber,
        String insuranceTypeValue,
        String insuranceTypeValueDescription,
        String companyBizNo,
        Double pubCorNoticeAmount,
        Double monthPremAmount,
        Double yrSettlePremAmount,
        Double employershareSumAmount,
        Long targetCount
) {
}
