package repos;

import config.TestConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.jpa.repos.EmployeeJpaRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class EmployeeJpaRepositoryTest {

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Before
    public void createEmployeeTest() {

        CompanyJpa companyJpa = new CompanyJpa(2L);
        EmployeeJpa employeeJpa = new EmployeeJpa("ФИО_8_test", new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime(), companyJpa);
        employeeJpaRepository.save(employeeJpa);
    }

    @Test
    public void findAllTest(){
        List<EmployeeJpa> employees = employeeJpaRepository.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 8);
    }

    @Test
    public void findEmployeesByDateDismissalIsNullTest(){
        List<EmployeeJpa> employees = employeeJpaRepository.findByDateDismissalIsNull();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 4);
    }
    @Test
    public void findEmployeesByCompanyNameTest(){
        List<EmployeeJpa> employees = employeeJpaRepository.findAllByCompanyName("Компания_2_test");
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 1);
    }

    @After
    public void removeEmployeeTest(){
        Long maxId = employeeJpaRepository.getMaxEmployeeId();
        //Assert.assertNotNull(maxId);
        employeeJpaRepository.deleteById(maxId);
        /*
        List<EmployeeJpa> employees = employeeJpaRepository.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 7);
        */
    }



}
