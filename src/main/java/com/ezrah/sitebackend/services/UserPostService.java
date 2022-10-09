package com.ezrah.sitebackend.services;


import com.ezrah.sitebackend.entities.UserPost;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPostService {
    List<UserPost> getNewestDiscussions(Pageable pageable);

    UserPost findById(Integer id);
}
