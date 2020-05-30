package ru.examples.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyJdbc {

    private long id;

    private String name;

    private String address;

}
