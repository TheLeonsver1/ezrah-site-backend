package com.ezrah.sitebackend.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MinimalPageProps {
    private Boolean isLoggedIn;
    private Integer userId;
    private String userName;
}
