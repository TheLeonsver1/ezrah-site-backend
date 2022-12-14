package com.ezrah.sitebackend.bills.impl.services;

import com.ezrah.sitebackend.bills.Bill;
import com.ezrah.sitebackend.bills.impl.repositories.BillRepo;
import com.ezrah.sitebackend.comments.BillComment;
import com.ezrah.sitebackend.users.User;
import com.ezrah.sitebackend.comments.impl.repositories.BillCommentsRepo;
import com.ezrah.sitebackend.bills.BillService;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BillServiceImpl implements BillService {
    private final BillRepo billRepo;
    private final BillCommentsRepo billCommentsRepo;

    @Override
    public List<Bill> findLastUpdatedBills(Pageable pageable) {
        try {
            return billRepo.findAllByOrderByKnsLastUpdatedDateDesc(pageable);
        } catch (Exception e) {
            log.error("findLastUpdatedBills failed", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Bill findById(Integer id) {
        return billRepo.findById(id).orElse(null);
    }

    public List<BillComment> getBillComments(Integer billId, Pageable pageable) {
        return billCommentsRepo.findAllByBillIdAndDeletedFalseOrderByCreatedDateDesc(billId, pageable);
    }

    @Override
    @Transactional
    public BillComment appendComment(User user, Integer billId, String commentText) throws Exception {
        Assert.notNull(billId);
        Assert.hasText(commentText);
        Optional<Bill> bill = billRepo.findById(billId);
        if (bill.isPresent()) {
            BillComment billComment = new BillComment();
            billComment.setBill(bill.get());
            billComment.setInitialContentVersion(commentText);
            billComment.setLatestContentVersion(commentText);
            billComment.setPostCreator(user);
            return billCommentsRepo.save(billComment);
        } else {
            throw new Exception("Bill not found");
        }
    }
}
