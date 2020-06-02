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
import ru.examples.jpa.repos.CompanyJpaRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class CompanyJpaRepositoryTest {

    @Autowired
    private CompanyJpaRepository companyJpaRepository;

    @Before
    public void createCompanyTest(){
        CompanyJpa companyJpa = new CompanyJpa("Компания_5_test","Адрес_6_test");
        companyJpaRepository.save(companyJpa);
    }

    @Test
    public void findAllTest(){
        List<CompanyJpa> companies = companyJpaRepository.findAll();
        Assert.assertNotNull(companies);
        Assert.assertEquals(companies.size(), 6);
    }

    @Test
    public void findByNameTest(){
        List<CompanyJpa> companies = companyJpaRepository.findByName("Компания_5_test");
        Assert.assertNotNull(companies);
        Assert.assertEquals(companies.size(), 2);
    }

    @After
    public void removeCompanyTest(){
        Long maxId = companyJpaRepository.getMaxCompanyId();
        Assert.assertNotNull(maxId);
        companyJpaRepository.deleteById(maxId);
        List<CompanyJpa> companies = companyJpaRepository.findAll();
        Assert.assertNotNull(companies);
        Assert.assertEquals(companies.size(), 5);
    }
}
