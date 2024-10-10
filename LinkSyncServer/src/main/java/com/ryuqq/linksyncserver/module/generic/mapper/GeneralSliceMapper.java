package com.ryuqq.linksyncserver.module.generic.mapper;

import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import com.ryuqq.linksyncserver.module.generic.LastDomainIdProvider;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralSliceMapper<T extends LastDomainIdProvider> {

    CustomSlice<T> toSlice(List<T> data, Pageable pageable);
    CustomSlice<T> toSlice(List<T> data, Pageable pageable, long totalElements);

}


