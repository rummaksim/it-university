package ru.examples.jpa.entity;

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
public class EmployeeJpa {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="date_start", nullable = false)
    private Date dateStart;

    @Column(name="date_dismissal")
    private Date dateDismissal;

    @ManyToOne
    @JoinColumn(name="company", referencedColumnName = "id", nullable = false)
    private CompanyJpa company;
}
