package service.employee;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.exceptions.EntityIllegalArgumentException;
import ru.examples.exceptions.EntityNotFoundException;
import ru.examples.jpa.entity.EmployeeJpa;
import ru.examples.service.impl.DefaultEmployeeService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class EmployeeJpaServiceFindTests {

    @Autowired
    private DefaultEmployeeService defaultEmployeeService;

    @Test
    public void findAllEmployeesTest(){
        List<EmployeeJpa> employees = defaultEmployeeService.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 7);
    }
    @Test(expected = EntityIllegalArgumentException.class)
    public void findEmployeeByNullIdTest(){
        defaultEmployeeService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findEmployeeByIllegalTypeIdTest(){
        defaultEmployeeService.findById("id 1");
    }

    @Test (expected = EntityNotFoundException.class)
    public void findNonexistentEmployeeByIdTest(){
        defaultEmployeeService.findById(8);
    }

    @Test
    public void findEmployeeByIdTest(){
        EmployeeJpa employee = defaultEmployeeService.findById(1);
        Assert.assertNotNull(employee);
    }
}
