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
public class EmployeeJpaServiceDeleteTests {

    @Autowired
    private DefaultEmployeeService defaultEmployeeService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteEmployeeByNullId(){
        defaultEmployeeService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteEmployeeByIllegalTypeId(){
        defaultEmployeeService.delete("id_1");
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNonexistentEmployeeTest(){
        defaultEmployeeService.delete(10);
    }

    @Test
    public void deleteEmployeeTest(){
        defaultEmployeeService.delete(7);
        List<EmployeeJpa> employees = defaultEmployeeService.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(employees.size(), 6);
    }

}
