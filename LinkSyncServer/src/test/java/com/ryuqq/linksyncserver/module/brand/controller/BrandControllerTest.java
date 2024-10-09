package com.ryuqq.linksyncserver.module.brand.controller;

import com.ryuqq.linksyncserver.data.brand.BrandModuleHelper;
import com.ryuqq.linksyncserver.document.utils.RestDocsTestSupport;
import com.ryuqq.linksyncserver.module.brand.dto.command.CreateBrandRequest;
import com.ryuqq.linksyncserver.module.brand.dto.query.BrandResponse;
import com.ryuqq.linksyncserver.module.brand.service.command.BrandCommandService;
import com.ryuqq.linksyncserver.module.brand.service.query.BrandQueryService;
import com.ryuqq.linksyncserver.module.brand.validator.BrandValidator;
import com.ryuqq.linksyncserver.module.site.service.LanguageCodeQueryService;

import com.ryuqq.linksyncserver.module.site.validator.LanguageCodeValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandControllerTest extends RestDocsTestSupport {

    @MockBean
    private BrandCommandService brandCommandService;

    @MockBean
    protected BrandValidator roleChecker;

    @MockBean
    protected LanguageCodeValidator languageCodeValidator;

    @MockBean
    protected BrandQueryService brandQueryService;

    @MockBean
    protected LanguageCodeQueryService languageCodeQueryService;

    @Test
    void shouldCreateBrandSuccessfully() throws Exception {
        BrandResponse brandResponse = BrandModuleHelper.toBrandResponse(1L, "NewBrand", "en");

        when(brandQueryService.brandExists(brandResponse.getBrandName(), brandResponse.getLanguageCode()))
                .thenReturn(false);

        when(brandCommandService.createBrand(any(CreateBrandRequest.class)))
                .thenReturn(brandResponse);

        CreateBrandRequest request = BrandModuleHelper.toCreateBrandRequest("NewBrand", "en");

        mockMvc.perform(post("/api/v1/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("create-brand",
                            requestFields(
                                    fieldWithPath("brandName").type(JsonFieldType.STRING).description("The brand name"),
                                    fieldWithPath("languageCode").type(JsonFieldType.STRING).description("The language code")
                            ),
                            responseFields(
                                    beneathPath("data"),
                                    fieldWithPath("brandId").type(JsonFieldType.NUMBER).description("The brand ID"),
                                    fieldWithPath("brandName").type(JsonFieldType.STRING).description("The brand name"),
                                    fieldWithPath("languageCode").type(JsonFieldType.STRING).description("The language code")
                            ),
                            responseFields(
                                    beneathPath("response"),
                                    statusMsg()
                            )
                        )
                );
    }

    @Test
    void shouldReturnBadRequestWhenInvalidRequest() throws Exception {
        CreateBrandRequest request = new CreateBrandRequest("", "en");

        mockMvc.perform(post("/api/v1/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(
                        document("create-brand-invalid",
                            requestFields(
                                    fieldWithPath("brandName").type(JsonFieldType.STRING).description("The brand name"),
                                    fieldWithPath("languageCode").type(JsonFieldType.STRING).description("The language code")
                            ),
                            responseFields(
                                    errorStatusMsg()
                            )
                        )
                );
    }

}