package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Any type of textual post a user can create, whether a post on a bill, or a comment on another user's post, or anything else
 */
@Entity
@Table(name = "bill_comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class BillComment extends UserPost {
    @ManyToOne
    @ToString.Exclude
    private Bill bill;


}
