package com.ezrah.sitebackend.reactions;

import lombok.ToString;

import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@MappedSuperclass
public class Reactable {

    @OneToOne(fetch = FetchType.LAZY)
    private ReactionStats reactionStats;

    @OneToMany
    @ToString.Exclude
    private List<Reaction> reactions;
}
