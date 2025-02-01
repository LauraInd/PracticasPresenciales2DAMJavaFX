package com.example.javafxapp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private long id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private boolean active;

}
