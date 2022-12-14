package com.ezrah.sitebackend.bills.impl.repositories;

import com.ezrah.sitebackend.bills.Bill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends PagingAndSortingRepository<Bill, Integer>, CrudRepository<Bill, Integer> {
    List<Bill> findAllByOrderByKnsLastUpdatedDateDesc(Pageable pageable);

}
