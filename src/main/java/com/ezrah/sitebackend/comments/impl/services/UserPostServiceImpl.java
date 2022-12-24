package com.ezrah.sitebackend.comments.impl.services;

import com.ezrah.sitebackend.comments.UserPost;
import com.ezrah.sitebackend.comments.UserPostService;
import com.ezrah.sitebackend.comments.impl.repositories.UserPostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepo userPostRepo;

    @Override
    public List<UserPost> getNewestDiscussions(Pageable pageable) {
        try {
            return userPostRepo.findAllByOrderByCreatedDateDesc(pageable);
        } catch (Exception e) {
            log.error("getNewestDiscussions failed", e);
            return Collections.emptyList();
        }
    }

    @Override
    public UserPost findById(Integer id) {
        return userPostRepo.findById(id).orElse(null);
    }


}
