package com.ezrah.sitebackend.base.objects;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class Paging {
    private Integer page;
    private Integer size;

//    private String sortDirection;

//    private String sortOn;

    public PageRequest toPageRequest() {
//        if(StringUtils.hasText(sort)){
//            if(sort.equals("ASC")){
//                sortObj
//            }
//        }
        return PageRequest.of(page, size);
    }
}
