package com.force.labor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.math.BigInteger;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@Entity(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_ids_gen")
    @SequenceGenerator(name = "employees_ids_gen", sequenceName = "employees_id_seq", allocationSize = 1)
    private BigInteger id;

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 11)
    private String passport;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    private Integer salaryPerHour;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

}
