package service.employee;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.exceptions.EntityAlreadyExistsException;
import ru.examples.exceptions.EntityIllegalArgumentException;
import ru.examples.exceptions.EntityNotFoundException;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.service.impl.DefaultEmployeeService;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class EmployeeJpaServiceCreateTests {

    @Autowired
    private DefaultEmployeeService defaultEmployeeService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCompanyTest(){
        defaultEmployeeService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createEmployeeWithNullIdTest(){
        CompanyJpa company = new CompanyJpa(2L);
        EmployeeJpa employee = new EmployeeJpa(null, "ФИО_new",
                new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(), company);
        defaultEmployeeService.create(employee);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createEmployeeWithNullDateStartTest(){
        CompanyJpa company = new CompanyJpa(2L);
        EmployeeJpa employee = new EmployeeJpa(8L, "ФИО_new", null,
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(), company);
        defaultEmployeeService.create(employee);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createEmployeeWithNullCompanyTest(){
        EmployeeJpa employee = new EmployeeJpa(8L, "ФИО_new",
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(), null, null);
        defaultEmployeeService.create(employee);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createEmployeeWithNullCompanyIdTest(){
        CompanyJpa company = new CompanyJpa(null);
        EmployeeJpa employee = new EmployeeJpa(8L, "ФИО_new",
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(),
                null, company);
        defaultEmployeeService.create(employee);
    }

    @Test(expected = EntityNotFoundException.class)
    public void createEmployeeWithNonexistentCompanyIdTest(){
        CompanyJpa company = new CompanyJpa(10L);
        EmployeeJpa employee = new EmployeeJpa(8L, "ФИО_new",
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(),
                null, company);
        defaultEmployeeService.create(employee);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createEmployeeWithExistentIdTest(){
        CompanyJpa company = new CompanyJpa(2L);
        EmployeeJpa employee = new EmployeeJpa(7L, "ФИО_new",
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(),
                null, company);
        defaultEmployeeService.create(employee);
    }

    @Test
    public void createEmployeeTest(){
        CompanyJpa company = new CompanyJpa(2L);
        EmployeeJpa employee = new EmployeeJpa(8L, "ФИО_new",
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(),
                null, company);
        EmployeeJpa createdEmployee = defaultEmployeeService.create(employee);
        Assert.assertNotNull(createdEmployee);
    }
}
