package service.mock;

import org.springframework.stereotype.Service;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.service.IEntityService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class MockEmployeeJpaService implements IEntityService<EmployeeJpa> {
    @Override
    public List<EmployeeJpa> findAll() {
        return new ArrayList<>();
    }

    @Override
    public EmployeeJpa findById(Object id) {
        return new EmployeeJpa(Long.valueOf(id.toString()), "ФИО_test",
                new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                null, new CompanyJpa(1L));
    }

    @Override
    public EmployeeJpa create(EmployeeJpa employee) {
        return employee;
    }

    @Override
    public EmployeeJpa update(EmployeeJpa employee) {
        return employee;
    }

    @Override
    public void delete(Object id) {

    }
}
