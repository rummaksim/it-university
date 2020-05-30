package ru.examples.jdbc.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.examples.jdbc.entity.CompanyJdbc;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CompanyJdbcRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int companiesCount(){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.company", Integer.class);
    }

    public List<CompanyJdbc> getCompanies(){
        return  jdbcTemplate.query("SELECT * FROM public.company", (ResultSet rs, int rowNum) ->
                new CompanyJdbc(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("address")
                ));
    }
}
