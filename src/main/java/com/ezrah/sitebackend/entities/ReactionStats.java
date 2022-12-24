package com.ezrah.sitebackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reaction_stats")
public class ReactionStats {
    @Id
    private Integer id;

    private int happyCount;
    private int sadCount;
    private int angryCount;
    private int enlightenedCount;
    private int surprisedCount;
    private int upvoteCount;
    private int downvoteCount;

}
