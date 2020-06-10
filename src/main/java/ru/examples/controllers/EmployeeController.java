package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.service.IEntityService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final IEntityService<EmployeeJpa> employeeService;

    @Autowired
    public EmployeeController(IEntityService<EmployeeJpa> employeeService) {
        this.employeeService = employeeService;
    }

    /*
    Настройка прав в SecurityUserDetailsManager.java
     */

    @GetMapping
    @PreAuthorize("hasPermission('employee', 'read')")
    public List<EmployeeJpa> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('employee', 'read')")
    public EmployeeJpa findById(@PathVariable String id){
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasPermission('employee', 'create')")
    public EmployeeJpa create(@RequestBody EmployeeJpa employee){
        return employeeService.create(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('employee', 'update')")
    public EmployeeJpa update(@RequestBody EmployeeJpa employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission('employee', 'delete')")
    public void delete(@PathVariable String id){
        employeeService.delete(id);
    }
}
