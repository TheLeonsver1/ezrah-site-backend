package com.ezrah.sitebackend.entities;

import javax.persistence.*;

@Entity
@Table(name = "israeli_laws")
public class IsraeliLaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    Ministry ministryInCharge;

}
