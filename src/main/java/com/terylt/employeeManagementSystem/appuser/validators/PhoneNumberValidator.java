package com.terylt.employeeManagementSystem.appuser.validators;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PhoneNumberValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        String kz = "\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}";
        //TODO: Add cz number regex
        String cz = "\\d{3}-\\d{3}-\\d{3}-\\d{3}";
        //TODO: s.matches(kz) || s.matches(cz)
        return s.matches(kz) || s.matches(cz);
    }
}
