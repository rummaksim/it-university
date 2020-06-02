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
@AllArgsConstructor
public class CompanyJpa {

    public static String TYPE_NAME = "Компания";

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    public CompanyJpa(String name, String address){
        this.name = name;
        this.address = address;
    }

    public CompanyJpa (Long id) {
        this.id = id;
        this.name = null;
        this.address = null;
    }
}
