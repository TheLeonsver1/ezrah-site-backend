package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

/**
 * A person who is related to the knesset, maps to a KNS_Person in knesset api
 */
@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true)
    Integer knsId;

    String firstName;

    String lastName;

    String email;

    @OneToMany(mappedBy = "person")
    List<PersonPosition> positionHistory;

    @OneToMany(mappedBy = "person")
    List<BillInitiator> billsInitiated;
}
