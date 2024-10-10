package com.ryuqq.linksyncserver.module.generic.mapper;

import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import com.ryuqq.linksyncserver.module.generic.LastDomainIdProvider;
import com.ryuqq.linksyncserver.module.generic.utils.SliceUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractGeneralSliceMapper <T extends LastDomainIdProvider> implements GeneralSliceMapper<T>{

    @Override
    public CustomSlice<T> toSlice(List<T> data, Pageable pageable) {
        Slice<T> slice = SliceUtils.toSlice(data, pageable);

        Long lastDomainId = null;

        if (!slice.isEmpty()) {
            List<T> content = slice.getContent();
            T t = content.get(content.size() - 1);
            lastDomainId = t.getId();
        }

        return buildCustomSlice(slice, null, lastDomainId);
    }

    @Override
    public CustomSlice<T> toSlice(List<T> data, Pageable pageable, long totalElements) {
        Slice<T> slice = SliceUtils.toSlice(data, pageable);
        Long lastDomainId = null;

        if (!slice.isEmpty()) {
            List<T> content = slice.getContent();
            T t = content.get(content.size() - 1);
            lastDomainId = t.getId();
        }

        return buildCustomSlice(slice, totalElements, lastDomainId);
    }


    private CustomSlice<T> buildCustomSlice(Slice<T> slice, Long totalElements, Long lastDomainId){
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

