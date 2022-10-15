package com.ezrah.sitebackend.repositories;

import com.ezrah.sitebackend.entities.UserPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostRepo extends PagingAndSortingRepository<UserPost, Integer> {
    List<UserPost> findAllByOrderByCreatedDateDesc(Pageable pageable);

//    @Query(nativeQuery = true, "select count(happy) from user_posts u inner join user_posts_reactions upr on u.id = upr. join reactions r on upr.id where r.happy = true")
//    UserPost findUserPostWithReactionCounts();
}
