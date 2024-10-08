package com.ryuqq.linksyncserver.module.brand.service.command;

import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;

public interface BrandCommandService {

    BrandResponse createBrand(CreateBrandRequest request);


}
