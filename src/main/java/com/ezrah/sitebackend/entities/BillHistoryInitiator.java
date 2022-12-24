package com.ezrah.sitebackend.entities;

import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * This exists because I don't know if KNS_BillInitiator and KNS_BillHistoryInitiator share ids
 * but this entity is for bill initiators that are no longer bill initiators for whatever reason
 */
@Entity
@Table(name = "bill_history_initiators")
@ToString
public class BillHistoryInitiator extends BaseBillInitiator {

    Integer knsReasonId;

    String reasonStoppedDescription;
}
