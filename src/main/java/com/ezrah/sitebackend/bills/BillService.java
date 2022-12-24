package com.ezrah.sitebackend.bills;


import com.ezrah.sitebackend.comments.BillComment;
import com.ezrah.sitebackend.users.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillService {
    List<Bill> findLastUpdatedBills(Pageable pageable);

    Bill findById(Integer id);

    List<BillComment> getBillComments(Integer billId, Pageable pageable);

    BillComment appendComment(User user, Integer billId, String commentText) throws Exception;
}
