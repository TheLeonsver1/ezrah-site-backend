package com.ezrah.sitebackend.controllers;

import com.ezrah.sitebackend.base.common.PagingVisitor;
import com.ezrah.sitebackend.bills.Bill;
import com.ezrah.sitebackend.comments.BillComment;
import com.ezrah.sitebackend.users.User;
import com.ezrah.sitebackend.comments.UserPost;
import com.ezrah.sitebackend.base.objects.LoggedInUser;
import com.ezrah.sitebackend.base.objects.Paging;
import com.ezrah.sitebackend.bills.BillService;
import com.ezrah.sitebackend.comments.UserPostService;
import com.ezrah.sitebackend.users.UserService;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import jakarta.validation.constraints.NotEmpty;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GraphQLController {

    private final UserService userService;

    private final BillService billService;

    private final UserPostService userPostService;

    @QueryMapping
    public LoggedInUser loggedInUser(Principal principal) {
        LoggedInUser.LoggedInUserBuilder loggedInUserBuilder = LoggedInUser.builder();
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            loggedInUserBuilder.isLoggedIn(true);
            loggedInUserBuilder.userId(user.getId());
            loggedInUserBuilder.userName(user.getUsername());
        } else {
            loggedInUserBuilder.isLoggedIn(false);
        }
        return loggedInUserBuilder.build();
    }

    @QueryMapping
    public List<Bill> getBills(@Argument Paging paging) {
        return billService.findLastUpdatedBills(PagingVisitor.convertToPageRequest(paging));
    }

    @QueryMapping
    public Bill getBill(@Argument Integer id) {
        return billService.findById(id);
    }

    @QueryMapping
    public List<UserPost> getPosts(@Argument Paging paging) {
        return userPostService.getNewestDiscussions(PagingVisitor.convertToPageRequest(paging));
    }

    @QueryMapping
    public UserPost getPost(@Argument Integer id) {
        return userPostService.findById(id);
    }

    @QueryMapping
    public List<BillComment> getBillComments(@Argument Integer billId, @Argument Paging paging) {
        return billService.getBillComments(billId, PagingVisitor.convertToPageRequest(paging));
    }

    @MutationMapping
    public BillComment addBillComment(Principal principal, @Argument Integer billId, @Argument @NotEmpty String commentText) throws Exception {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, principal, "User isn't logged in ");
        return billService.appendComment((User) ((Authentication) principal).getPrincipal(), billId, commentText);
    }
}
