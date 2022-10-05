package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A knesset item type, maps to a KNS_Type in knesset api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String description;

}
