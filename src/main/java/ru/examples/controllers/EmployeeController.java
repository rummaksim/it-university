package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeJpa> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeJpa findById(@PathVariable String id){
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeJpa create(@RequestBody EmployeeJpa employee){
        return employeeService.create(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeJpa update(@RequestBody EmployeeJpa employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        employeeService.delete(id);
    }
}
