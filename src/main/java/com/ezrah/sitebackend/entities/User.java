package com.ezrah.sitebackend.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private boolean deleted;
    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities")
    private List<Authority> authorities;

    @OneToMany
    @JoinTable(name = "posts_by_user")
    public List<UserPost> createdPosts;

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return !deleted;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return !deleted;
    }


}
