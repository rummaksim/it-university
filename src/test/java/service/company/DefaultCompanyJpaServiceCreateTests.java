package service.company;

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
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.impl.DefaultCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultCompanyJpaServiceCreateTests {

    @Autowired
    private DefaultCompanyService companyService;

    @Test
    public void createCompanyTest(){
        CompanyJpa company = new CompanyJpa(6L,"Компания_6", "Адрес_6");
        CompanyJpa savedCompany = companyService.create(company);
        Assert.assertNotNull(savedCompany);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCompanyTest(){
        companyService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createCompanyWithNullIdTest(){
        CompanyJpa company = new CompanyJpa(null);
        companyService.create(company);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createCompanyWithNullNameTest(){
        CompanyJpa company = new CompanyJpa(null, "Адрес");
        companyService.create(company);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createCompanyWithNullAddressTest(){
        CompanyJpa company = new CompanyJpa("Название", null);
        companyService.create(company);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createCompanyWithExistedIdTest(){
        CompanyJpa company = new CompanyJpa(1L,"Название_6","Адрес_6");
        companyService.create(company);
    }
}
