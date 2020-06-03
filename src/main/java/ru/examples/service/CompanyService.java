package ru.examples.service;

import ru.examples.jpa.entity.CompanyJpa;

import java.util.List;

public interface CompanyService {

    List<CompanyJpa> findAll();

    CompanyJpa findById(Object id);

    CompanyJpa create(CompanyJpa company);

    CompanyJpa update(CompanyJpa company);

    void delete(Object id);
}
