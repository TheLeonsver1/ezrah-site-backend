package com.ezrah.sitebackend.base.common;

import com.ezrah.sitebackend.base.objects.Paging;
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
