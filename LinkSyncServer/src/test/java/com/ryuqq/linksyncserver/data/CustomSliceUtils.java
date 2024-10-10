package com.ryuqq.linksyncserver.data;

import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import com.ryuqq.linksyncserver.module.generic.LastDomainIdProvider;
import com.ryuqq.linksyncserver.module.generic.utils.SliceUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public class CustomSliceUtils {

    public static <T extends LastDomainIdProvider> CustomSlice<T> buildCustomSlice(List<T> data, Pageable pageable, long totalElements) {
        Slice<T> slice = SliceUtils.toSlice(data, pageable);
        Long lastDomainId = null;

        if (!slice.isEmpty()) {
            List<T> content = slice.getContent();
            T lastItem = content.getLast();
            lastDomainId = lastItem.getId();
        }

        return CustomSlice.<T>builder()
                .content(slice.getContent())
                .last(slice.isLast())
                .first(slice.isFirst())
                .sort(slice.getSort())
                .size(slice.getSize())
                .numberOfElements(slice.getNumberOfElements())
                .empty(slice.isEmpty())
                .lastDomainId(lastDomainId)
                .totalElements(totalElements)
                .build();
    }

}
