package com.ezrah.sitebackend.services;


import com.ezrah.sitebackend.entities.Bill;
import com.ezrah.sitebackend.entities.BillComment;
import com.ezrah.sitebackend.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillService {
    List<Bill> findLastUpdatedBills(Pageable pageable);

    Bill findById(Integer id);

    List<BillComment> getBillComments(Integer billId, Pageable pageable);

    BillComment appendComment(User user, Integer billId, String commentText) throws Exception;
}
