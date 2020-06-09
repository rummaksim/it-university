package ru.examples.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.examples.annotation.Loggable;
import ru.examples.exceptions.EntityAlreadyExistsException;
import ru.examples.exceptions.EntityHasDetailsException;
import ru.examples.exceptions.EntityIllegalArgumentException;
import ru.examples.exceptions.EntityNotFoundException;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.jpa.repos.CompanyJpaRepository;
import ru.examples.jpa.repos.EmployeeJpaRepository;
import ru.examples.service.IEntityService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCompanyService implements IEntityService<CompanyJpa> {

    private final CompanyJpaRepository companyJpaRepository;

    private final EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public DefaultCompanyService(CompanyJpaRepository companyJpaRepository, EmployeeJpaRepository employeeJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
        this.employeeJpaRepository = employeeJpaRepository;
    }

    public List<CompanyJpa> findAll(){
        return companyJpaRepository.findAll();
    }

    public CompanyJpa findById(Object id){
        Optional<CompanyJpa> company;
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
        company = companyJpaRepository.findById(parsedId);
        if (!company.isPresent()){
            throw new EntityNotFoundException(CompanyJpa.TYPE_NAME, parsedId);
        }
        return company.get();
    }

    public CompanyJpa create(CompanyJpa company){
        if (company == null){
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (company.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        if (company.getName() == null){
            throw new EntityIllegalArgumentException("Название компании не может быть null");
        }
        if (company.getAddress() == null){
            throw new EntityIllegalArgumentException("Адрес компании не может быть null");
        }
        Optional<CompanyJpa> existedCompany = companyJpaRepository.findById(company.getId());

        if (existedCompany.isPresent()){
            throw new EntityAlreadyExistsException(CompanyJpa.TYPE_NAME, company.getId());
        }
        return companyJpaRepository.save(company);
    }

    @Override
    public CompanyJpa update(CompanyJpa company) {
        if (company == null){
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (company.getId() == null){
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        if (company.getName() == null){
            throw new EntityIllegalArgumentException("Название компании не может быть null");
        }
        if (company.getAddress() == null){
            throw new EntityIllegalArgumentException("Адрес компании не может быть null");
        }
        Optional<CompanyJpa> existedCompany = companyJpaRepository.findById(company.getId());

        if (!existedCompany.isPresent()){
            throw new EntityNotFoundException(CompanyJpa.TYPE_NAME, company.getId());
        }
        return companyJpaRepository.save(company);
    }

    public void delete(Object id){
        CompanyJpa company = findById(id);
        List<EmployeeJpa> employees = employeeJpaRepository.findByCompany(company);
        if (employees.size()>0){
            throw new EntityHasDetailsException(EmployeeJpa.TYPE_NAME, company.getId());
        }
        companyJpaRepository.delete(company);
    }
}
