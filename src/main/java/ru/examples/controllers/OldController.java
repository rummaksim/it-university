package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.examples.jdbc.entity.CompanyJdbc;
import ru.examples.jdbc.entity.EmployeeJdbc;
import ru.examples.jdbc.repos.CompanyJdbcRepository;
import ru.examples.jdbc.repos.EmployeeJdbcRepository;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.jpa.repos.CompanyJpaRepository;
import ru.examples.jpa.repos.EmployeeJpaRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OldController {

    private final CompanyJpaRepository companyJpaRepository;

    private final EmployeeJpaRepository employeeJpaRepository;

    private final CompanyJdbcRepository companyJdbcRepository;

    private final EmployeeJdbcRepository employeeJdbcRepository;

    @Autowired
    public OldController(CompanyJpaRepository companyJpaRepository, EmployeeJpaRepository employeeJpaRepository, CompanyJdbcRepository companyJdbcRepository, EmployeeJdbcRepository employeeJdbcRepository) {
        this.companyJpaRepository = companyJpaRepository;
        this.employeeJpaRepository = employeeJpaRepository;
        this.companyJdbcRepository = companyJdbcRepository;
        this.employeeJdbcRepository = employeeJdbcRepository;
    }

    @GetMapping("jpa/companies")
    public List<CompanyJpa> getAllCompanies(){
        return companyJpaRepository.findAll();
    }

    @GetMapping("jpa/companies/company")
    public List<CompanyJpa> findCompanyByName(){
        return companyJpaRepository.findByName("Компания_2");
    }

    @GetMapping("jpa/employees")
    public List<EmployeeJpa> getAllEmployees(){
        return employeeJpaRepository.findAll();
    }

    @GetMapping("jpa/employees/current")
    public List<EmployeeJpa> getEmployeesByDateDismissalIsNull(){
        return employeeJpaRepository.findByDateDismissalIsNull();
    }

    @GetMapping("jpa/company/employees/count")
    public Integer getCountEmployeesByCompany(){
        return employeeJpaRepository.getCountEmployeesByCompany(2);
    }

    @GetMapping("jpa/company/employees/info")
    public List<EmployeeJpa> getEmployeesByCompanyName(){
        return employeeJpaRepository.findAllByCompanyName("Компания_5");
    }

    @PostMapping("jpa/company/add/employee")
    public EmployeeJpa addEmployeeJpa(@RequestBody EmployeeJpa employeeJpa){
        return employeeJpaRepository.save(employeeJpa);
    }

    @GetMapping("jdbc/companies/count")
    public int getCompaniesCountJdbc(){
        return companyJdbcRepository.companiesCount();
    }

    @GetMapping("jdbc/companies")
    public List<CompanyJdbc> getAllCompaniesJdbc(){
        return companyJdbcRepository.getCompanies();
    }

    @GetMapping("jdbc/companies/active")
    public List<CompanyJdbc> getCompaniesWithActiveWorkersJdbc(){
        return employeeJdbcRepository.getCompaniesWithActiveWorkers();
    }

    @PostMapping("jdbc/company/add/employee")
    public int addEmployeeJdbc(@RequestBody EmployeeJdbc employeeJdbc){
        return employeeJdbcRepository.addEmployee(employeeJdbc);
    }
}
