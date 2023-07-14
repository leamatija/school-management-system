package com.internship.spring.project.schoolmanagementsystem.repository.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchQuery {
    private String key;
    private String operation;
    private Object value;

    public SearchQuery(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

}