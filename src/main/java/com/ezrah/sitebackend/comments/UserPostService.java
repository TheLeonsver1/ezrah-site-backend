package com.ezrah.sitebackend.comments;


import com.ezrah.sitebackend.comments.UserPost;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPostService {
    List<UserPost> getNewestDiscussions(Pageable pageable);

    UserPost findById(Integer id);
}
