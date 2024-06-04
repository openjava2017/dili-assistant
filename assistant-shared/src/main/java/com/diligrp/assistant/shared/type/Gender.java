package com.diligrp.assistant.shared.type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum Gender implements IEnumType {
    MALE("男", 1),
    FEMALE("女", 2);

    private String name;
    private int code;

    Gender(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Optional<Gender> getGender(int code) {
        Stream<Gender> GENDERS = Arrays.stream(values());
        return GENDERS.filter((gender) -> gender.getCode() == code).findFirst();
    }

    public static String getName(int code) {
        Stream<Gender> GENDERS = Arrays.stream(values());
        Optional<String> result = GENDERS.filter((gender) -> gender.getCode() == code)
            .map(Gender::getName).findFirst();
        return result.isPresent() ? result.get() : null;
    }

    public static List<Gender> getGenders() {
        return Arrays.asList(values());
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return this.name;
    }
}