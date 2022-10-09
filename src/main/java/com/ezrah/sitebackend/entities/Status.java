package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * An item's status in the knesset, maps to a KNS_Status in knesset api
 */
@Entity
@Table(name = "statuses")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String description;
}
