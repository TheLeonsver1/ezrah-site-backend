package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * An item's status in the knesset, maps to a KNS_Status in knesset api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String description;
}
