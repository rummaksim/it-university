package service.company;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.exceptions.EntityHasDetailsException;
import ru.examples.exceptions.EntityIllegalArgumentException;
import ru.examples.exceptions.EntityNotFoundException;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.impl.DefaultCompanyService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultCompanyJpaServiceDeleteTests {

    @Autowired
    private DefaultCompanyService companyService;

    @Test
    public void deleteCompanyTest(){
        Object id = 2;
        companyService.delete(id);
        List<CompanyJpa> companies = companyService.findAll();
        Assert.assertNotNull(companies);
        Assert.assertEquals(companies.size(), 4);

    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteCompanyWithNullIdTest(){
        companyService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteCompanyWithIllegalTypeIdTest(){
        companyService.delete("id 1");
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNonexistentCompanyByIdTest(){
        companyService.delete(10);
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteCompanyHasDetailsTest(){
        companyService.delete(1);
    }
}
