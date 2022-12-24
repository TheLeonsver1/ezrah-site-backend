package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A knesset bill, maps to a KNS_Bill in knesset api
 */
@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bill extends Reactable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer knsId;

    /**
     * The knesset number when the bill passed 3rd call
     */
    Integer knessetNum;

    /**
     * Bill name
     */
    String name;

    @ManyToOne
    Status status;

    Integer privateNumber;

    Integer governmentalNumber;

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

    /**
     * user comments on a bill
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    List<BillComment> comments;

    /**
     * The bill's subtype in the knesset api
     */
    Integer knsSubTypeId;

    String knsSubTypeDescription;

    @OneToMany(mappedBy = "bill")
    List<BillInitiator> billInitiators;
}
