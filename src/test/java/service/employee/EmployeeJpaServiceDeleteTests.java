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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class EmployeeJpaServiceDeleteTests {

    @Autowired
    private EmployeeService employeeService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteEmployeeByNullId(){
        employeeService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteEmployeeByIllegalTypeId(){
        employeeService.delete("id_1");
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNonexistentEmployeeTest(){
        employeeService.delete(10);
    }

    @Test
    public void deleteEmployeeTest(){
        employeeService.delete(7);
        List<EmployeeJpa> employees = employeeService.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 6);
    }

}
