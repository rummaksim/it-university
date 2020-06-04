package ru.examples.service;

import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeJpa> findAll();

    EmployeeJpa findById(Object id);

    EmployeeJpa create(EmployeeJpa employee);

    EmployeeJpa update(EmployeeJpa employee);

    void delete(Object id);
}
