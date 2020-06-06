package ru.examples.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.examples.jpa.entity.CompanyJpa;

import java.util.List;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyJpa, Long> {
    List<CompanyJpa> findByName(String name);

    @Query(value="SELECT MAX(id) FROM company", nativeQuery = true)
    Long getMaxCompanyId();
}
