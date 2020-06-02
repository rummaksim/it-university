package service.company;

import config.TestConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.exseptions.EntityIllegalArgumentException;
import ru.examples.exseptions.EntityNotFoundException;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.CompanyService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class CompanyJpaServiceFindTests {

    @Autowired
    private CompanyService companyService;

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
