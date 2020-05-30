package ru.examples.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeJdbc {

    private long id;

    private String name;

    private Date dateStart;

    private Date dateDismissal;

    private long company;

}
