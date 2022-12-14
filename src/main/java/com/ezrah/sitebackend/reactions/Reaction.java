package com.ezrah.sitebackend.reactions;

import com.ezrah.sitebackend.users.User;
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
