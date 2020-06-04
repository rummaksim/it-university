package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.ICompanyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final ICompanyService companyService;

    @Autowired
    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyJpa> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyJpa findById(@PathVariable String id){
        return companyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyJpa create(@RequestBody CompanyJpa company){
        return companyService.create(company);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CompanyJpa update(@RequestBody CompanyJpa company){
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        companyService.delete(id);
    }
}
