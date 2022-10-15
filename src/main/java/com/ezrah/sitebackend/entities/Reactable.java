package com.ezrah.sitebackend.entities;

import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@MappedSuperclass
public class Reactable {

    @OneToOne(fetch = FetchType.LAZY)
    private ReactionStats reactionStats;

    @OneToMany
    @ToString.Exclude
    private List<Reaction> reactions;
}
