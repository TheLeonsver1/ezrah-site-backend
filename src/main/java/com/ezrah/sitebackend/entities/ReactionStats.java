package com.ezrah.sitebackend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
