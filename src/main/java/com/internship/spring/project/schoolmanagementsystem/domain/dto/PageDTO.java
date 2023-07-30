package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageDTO {

    private int pageNumber;
    private int pageSize = 10;
    private String sortDirection = "ASC";
    private String sortBy= "firstName";

}
