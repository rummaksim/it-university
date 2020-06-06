package service.mock;

import org.springframework.stereotype.Service;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.service.IEntityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockCompanyJpaService implements IEntityService<CompanyJpa> {

    @Override
    public List<CompanyJpa> findAll() {
        return new ArrayList<>();
    }

    @Override
    public CompanyJpa findById(Object id) {
        return new CompanyJpa(Long.valueOf(id.toString()), "test_name", "test_address");
    }

    @Override
    public CompanyJpa create(CompanyJpa company) {
        return company;
    }

    @Override
    public CompanyJpa update(CompanyJpa company) {
        return company;
    }

    @Override
    public void delete(Object id) {

    }
}
