package ru.examples.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.examples.jpa.entity.EmployeeJpa;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeJpa, Long> {

    List<EmployeeJpa> findByDateDismissalIsNull();

    @Query(value="SELECT COUNT(*) FROM employee WHERE company = :company", nativeQuery = true)
    Integer getCountEmployeesByCompany(@Param("company") long company);

    List<EmployeeJpa> findAllByCompanyName(String companyName);

    @Query(value="SELECT MAX(id) FROM employee", nativeQuery = true)
    Long getMaxEmployeeId();




}
