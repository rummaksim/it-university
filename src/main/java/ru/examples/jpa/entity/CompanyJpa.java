package ru.examples.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
public class CompanyJpa {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    public CompanyJpa(String name, String address){
        this.name = name;
        this.address = address;
    }

    public CompanyJpa (long id) {
        this.id = id;
        this.name = null;
        this.address = null;
    }
}
