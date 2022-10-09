package com.ezrah.sitebackend.repositories;

import com.ezrah.sitebackend.entities.UserPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostRepo extends PagingAndSortingRepository<UserPost, Integer> {
    List<UserPost> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
