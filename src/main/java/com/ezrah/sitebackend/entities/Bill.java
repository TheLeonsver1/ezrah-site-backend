package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A knesset bill, maps to a KNS_Bill in knesset api
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    /**
     * The knesset number when the bill passed 3rd call
     */
    Integer knessetNum;

    /**
     * Bill name
     */
    String name;

    @ManyToOne
    ItemType subType;

    @ManyToOne
    Status status;

    /**
     * The official summary of the bill
     * Only has text if the bill passed 3rd call,
     */
    @Column(length = 3000)
    String officialLawSummary;

    /**
     * The datetime the bill was officially published to the public in the books
     */
    LocalDateTime publicationDate;

    /**
     * The datetime the bill last updated in the Knesset api
     */
    LocalDateTime knsLastUpdatedDate;
}
