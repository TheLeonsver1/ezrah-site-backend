package com.ezrah.sitebackend.comments;

import com.ezrah.sitebackend.bills.Bill;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

/**
 * Any type of textual post a user can create, whether a post on a bill, or a comment on another user's post, or anything else
 */
@Entity
@DiscriminatorValue(value = "BILL_COMMENT")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class BillComment extends UserPost {
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Bill bill;


}
