package com.ezrah.sitebackend.israeliLaw;

import com.ezrah.sitebackend.ministries.Ministry;
import jakarta.persistence.*;

@Entity
@Table(name = "israeli_laws")
public class IsraeliLaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    Ministry ministryInCharge;

}
