package com.ryuqq.linksyncserver.module.brand.filter;

import com.ryuqq.linksyncserver.module.generic.filter.AbstractCursorFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BrandFilter extends AbstractCursorFilter {

    private List<String> brandNames;
    private List<String> languageCodes;

}
