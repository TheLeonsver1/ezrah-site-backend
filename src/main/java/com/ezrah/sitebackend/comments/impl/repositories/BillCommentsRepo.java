package com.ezrah.sitebackend.comments.impl.repositories;

import com.ezrah.sitebackend.comments.BillComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillCommentsRepo extends PagingAndSortingRepository<BillComment, Integer>, CrudRepository<BillComment, Integer> {
    List<BillComment> findAllByBillIdAndDeletedFalseOrderByCreatedDateDesc(Integer billId, Pageable pageable);
}
