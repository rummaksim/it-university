package service.employee;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.exseptions.EntityIllegalArgumentException;
import ru.examples.exseptions.EntityNotFoundException;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.service.EmployeeService;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class EmployeeJpaServiceFindTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAllEmployeesTest(){
        List<EmployeeJpa> employees = employeeService.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 7);
    }
    @Test(expected = EntityIllegalArgumentException.class)
    public void findEmployeeByNullIdTest(){
        employeeService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findEmployeeByIllegalTypeIdTest(){
        employeeService.findById("id 1");
    }

    @Test (expected = EntityNotFoundException.class)
    public void findNonexistentEmployeeByIdTest(){
        employeeService.findById(8);
    }

    @Test
    public void findEmployeeByIdTest(){
        EmployeeJpa employee = employeeService.findById(1);
        Assert.assertNotNull(employee);
    }
}
