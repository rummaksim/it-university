package ru.examples.jdbc.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.examples.jdbc.entity.CompanyJdbc;
import ru.examples.jdbc.entity.EmployeeJdbc;

import java.util.Date;
import java.util.List;

@Repository
public class EmployeeJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CompanyJdbc> getCompaniesWithActiveWorkers(){
        return jdbcTemplate.query("SELECT DISTINCT c.id company_id, c.name company_name, c.address company_address FROM public.company c " +
                        "INNER JOIN public.employee e ON c.id = e.company WHERE e.date_dismissal IS NULL",
                (rs, rowNum)->
                        new CompanyJdbc(
                                rs.getLong("company_id"),
                                rs.getString("company_name"),
                                rs.getString("company_address")
                        ));
    }

    public int addEmployee(EmployeeJdbc employeeJdbc){
        return jdbcTemplate.update(
                "INSERT INTO public.employee (name, date_start, date_dismissal, company) VALUES (?, ?, ?, ?)",
                employeeJdbc.getName(), employeeJdbc.getDateStart(), employeeJdbc.getDateDismissal(), employeeJdbc.getCompany()
        );
    }
}
