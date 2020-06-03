package service.company;

import config.TestConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.exceptions.EntityIllegalArgumentException;
import ru.examples.exceptions.EntityNotFoundException;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.impl.DefaultCompanyService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultCompanyJpaServiceFindTests {

    @Autowired
    private DefaultCompanyService companyService;

    @Test
    public void findAllTest(){
        List<CompanyJpa> companies = companyService.findAll();
        Assert.assertNotNull(companies);
        Assert.assertEquals(companies.size(), 5);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findCompanyByNullIdTest(){
        companyService.findById(null);
    }
    @Test(expected = EntityIllegalArgumentException.class)
    public void findCompanyByIllegalTypeIdTest(){
        companyService.findById("id 1");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findNonexistentCompanyByIdTest(){
        companyService.findById(10);
    }

    @Test
    public void findCompanyTest(){
        CompanyJpa company = companyService.findById(2);
        Assert.assertNotNull(company);
    }
}
