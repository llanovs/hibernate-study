package org.test.demo.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AuthorBook {
    private String title;
    private String surname;
    private String name;
    private int pages;
}
