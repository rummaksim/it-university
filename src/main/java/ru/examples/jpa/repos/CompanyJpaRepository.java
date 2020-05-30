package ru.examples.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.examples.jpa.entity.CompanyJpa;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyJpa, Long> {
    CompanyJpa findByName(String name);
}
