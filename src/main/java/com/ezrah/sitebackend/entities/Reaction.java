package com.ezrah.sitebackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean happy;
    private boolean sad;
    private boolean angry;
    private boolean enlightened;
    private boolean surprised;
    private boolean upvote;

    @ManyToOne
    private User reactingUser;
}
