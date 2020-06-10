package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.IEntityService;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final IEntityService<CompanyJpa> companyService;

    @Autowired
    public CompanyController(IEntityService<CompanyJpa> companyService) {
        this.companyService = companyService;
    }

    /*
    Настройка прав в SecurityUserDetailsManager.java
     */

    @GetMapping
    @PreAuthorize("hasPermission('company', 'read')")
    public List<CompanyJpa> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('company', 'read')")
    public CompanyJpa findById(@PathVariable String id){
        return companyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasPermission('company', 'create')")
    public CompanyJpa create(@RequestBody CompanyJpa company){
        return companyService.create(company);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('company', 'update')")
    public CompanyJpa update(@RequestBody CompanyJpa company){
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission('company', 'delete')")
    public void delete(@PathVariable String id){
        companyService.delete(id);
    }
}
