package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Any type of textual post a user can create, whether a post on a bill, or a comment on another user's post, or anything else
 */
@Entity(name = "user_posts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    boolean deleted;

    boolean isRoot;

    /**
     * If exists, this is a comment on some other post, not a discussion root. this connection leads us to the first node of the discussion, <b>not</b> to this comment's parent
     */
    @ManyToOne
    private UserPost rootPost;

    /**
     * people's comments on this post/ comment
     */
    @ManyToMany
    @ToString.Exclude
    private List<UserPost> childComments;

    /**
     * When was this posted
     */
    @CreatedDate
    private LocalDateTime createdDate;

    /**
     * When was this last updated
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    /**
     * The first version of the post, it's persisted to be able to rebuild the post's revisions using the UserPostHistory diffs
     */
    private String initialContentVersion;

    /**
     * The most current version of the post, persisted to not rebuild the current version on every request
     */
    private String latestContentVersion;

    /**
     * Revision history(textual diffs) of the post
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userPost")
    @ToString.Exclude
    private List<UserPostRevision> history;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User postCreator;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private BillComment billComment;


}
