package com.ezrah.sitebackend.common;

import com.ezrah.sitebackend.objects.Paging;
import org.springframework.data.domain.PageRequest;

import java.util.Objects;

public class PagingVisitor {
    public static PageRequest convertToPageRequest(Paging paging) {
        if (Objects.nonNull(paging)) {
            return paging.toPageRequest();
        } else {
            return PageRequest.of(0, 10);
        }
    }
}
