package com.ezrah.sitebackend.controllers;

import com.ezrah.sitebackend.entities.User;
import com.ezrah.sitebackend.objects.MinimalPageProps;
import com.ezrah.sitebackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GraphQLController {

    private final UserService userService;

    @QueryMapping
    public MinimalPageProps minimalPageProps(Principal principal) {
        var pagePropsBuilder = MinimalPageProps.builder();
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            User user =(User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            pagePropsBuilder.isLoggedIn(true);
            pagePropsBuilder.userId(user.getId());
            pagePropsBuilder.userName(user.getUsername());
        } else {
            pagePropsBuilder.isLoggedIn(false);
        }
        return pagePropsBuilder.build();
    }

}
