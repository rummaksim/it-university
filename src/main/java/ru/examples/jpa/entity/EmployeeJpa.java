package ru.examples.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeJpa {

    public static String TYPE_NAME = "Сотрудник";

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="date_start", nullable = false)
    private Date dateStart;

    @Column(name="date_dismissal")
    private Date dateDismissal;

    @ManyToOne
    @JoinColumn(name="company", referencedColumnName = "id", nullable = false)
    private CompanyJpa company;

    public EmployeeJpa(String name, Date dateStart, Date dateDismissal, CompanyJpa company) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateDismissal = dateDismissal;
        this.company = company;
    }

}
