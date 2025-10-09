package com.example.server_9th.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    KOREAN_CUISINE("한식"),
    CHINESE_FOOD("중식"),
    JAPANESE_FOOD("일식"),
    WESTERN_FOOD("양식");

    private final String label;

    Category(String label){
        this.label=label;
    }

    @JsonValue
    public String getLabel(){
        return label;
    }


}
