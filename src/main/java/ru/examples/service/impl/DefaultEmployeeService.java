package ru.examples.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.examples.exceptions.EntityAlreadyExistsException;
import ru.examples.exceptions.EntityIllegalArgumentException;
import ru.examples.exceptions.EntityNotFoundException;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.jpa.repos.EmployeeJpaRepository;
import ru.examples.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeJpaRepository employeeJpaRepository;

    private final DefaultCompanyService companyService;

    @Autowired
    public DefaultEmployeeService(EmployeeJpaRepository employeeJpaRepository, DefaultCompanyService companyService) {
        this.employeeJpaRepository = employeeJpaRepository;
        this.companyService = companyService;
    }

    public List<EmployeeJpa> findAll(){
        return employeeJpaRepository.findAll();
    }

    public EmployeeJpa findById(Object id){
        Optional<EmployeeJpa> employee;
        if (id == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Long parsedId;
        try{
            parsedId = Long.valueOf(id.toString());
        }catch (NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор" +
                    "к нужному типу. Текст ошибки: %s", ex));
        }
        employee = employeeJpaRepository.findById(parsedId);
        if (!employee.isPresent()){
            throw new EntityNotFoundException(EmployeeJpa.TYPE_NAME, parsedId);
        }
        return employee.get();
    }

    public EmployeeJpa create(EmployeeJpa employee){
        if (employee == null){
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (employee.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        if (employee.getName() == null){
            throw new EntityIllegalArgumentException("Имя сотрудника не может быть null");
        }
        if (employee.getDateStart() == null){
            throw new EntityIllegalArgumentException("Дата начала работы сотрудника не может быть null");
        }
        if (employee.getCompany() == null){
            throw new EntityIllegalArgumentException("Компания не может быть null");
        }
        if (employee.getCompany().getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор компании не может быть null");
        }
        //Если компании нет в базе, выбросится исключение в companyService.findById()
        companyService.findById(employee.getCompany().getId());

        Optional<EmployeeJpa> existedEmployee = employeeJpaRepository.findById(employee.getId());
        if (existedEmployee.isPresent()){
            throw new EntityAlreadyExistsException(EmployeeJpa.TYPE_NAME, employee.getId());
        }
        return employeeJpaRepository.save(employee);
    }

    @Override
    public EmployeeJpa update(EmployeeJpa employee) {
        if (employee == null){
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (employee.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        if (employee.getName() == null){
            throw new EntityIllegalArgumentException("Имя сотрудника не может быть null");
        }
        if (employee.getDateStart() == null){
            throw new EntityIllegalArgumentException("Дата начала работы сотрудника не может быть null");
        }
        if (employee.getCompany() == null){
            throw new EntityIllegalArgumentException("Компания не может быть null");
        }
        if (employee.getCompany().getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор компании не может быть null");
        }
        //Если компании нет в базе, выбросится исключение в companyService.findById()
        companyService.findById(employee.getCompany().getId());

        Optional<EmployeeJpa> existedEmployee = employeeJpaRepository.findById(employee.getId());
        if (!existedEmployee.isPresent()){
            throw new EntityNotFoundException(EmployeeJpa.TYPE_NAME, employee.getId());
        }
        return employeeJpaRepository.save(employee);
    }

    public void delete(Object id){
        EmployeeJpa employee = findById(id);
        employeeJpaRepository.delete(employee);
    }

}
