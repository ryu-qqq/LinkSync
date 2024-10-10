package com.ryuqq.linksyncserver.data.brand;

import com.ryuqq.linksyncserver.data.CustomSliceUtils;
import com.ryuqq.linksyncserver.data.EasyRandomUtils;
import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.entity.Brand;
import com.ryuqq.linksyncserver.module.generic.CustomSlice;
import com.ryuqq.linksyncserver.module.generic.LastDomainIdProvider;
import org.jeasy.random.EasyRandom;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandModuleHelper {

    public static Brand toBrandWithNoId(){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("brandName", "Nike");
        stringObjectMap.put("languageCode", "en");
        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(Brand.class);
    }

    public static Brand toBrand(){
        EasyRandom instance = EasyRandomUtils.getInstance();
        return instance.nextObject(Brand.class);
    }

    public static Brand toBrand(String brandName, String languageCode){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("brandName", brandName);
        stringObjectMap.put("languageCode", languageCode);
        EasyRandom instance = EasyRandomUtils.getInstance(stringObjectMap);
        return instance.nextObject(Brand.class);
    }

    public static CreateBrandRequest toCreateBrandRequest(){
        EasyRandom instance = EasyRandomUtils.getInstance();
        return instance.nextObject(CreateBrandRequest.class);
    }

    public static CreateBrandRequest toCreateBrandRequest(String brandName, String languageCode){
        return new CreateBrandRequest("NewBrand", "en");
    }

    public static BrandResponse toBrandResponse(){
        EasyRandom instance = EasyRandomUtils.getInstance();
        return instance.nextObject(BrandResponse.class);
    }

    public static BrandResponse toBrandResponse(long brandId, String brandName, String languageCode){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("id", brandId);
        stringObjectMap.put("brandName", brandName);
        stringObjectMap.put("languageCode", languageCode);
        EasyRandom instance = EasyRandomUtils.getInstance(stringObjectMap);
        return instance.nextObject(BrandResponse.class);
    }

    public static CustomSlice<BrandResponse> toCustomBrandResponseSlice(List<BrandResponse> brandResponses, Pageable pageable, long totalElements) {
        return CustomSliceUtils.buildCustomSlice(brandResponses, pageable, totalElements);
    }


}
