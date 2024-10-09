package com.ryuqq.linksyncserver.document;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ryuqq.linksyncserver.document.utils.CustomResponseFieldsSnippet;
import com.ryuqq.linksyncserver.document.utils.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = {CommonDocController.class}
)
public class CommonDocControllerTest extends RestDocsTestSupport {

    @Test
    public void enums() throws Exception {

        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/test/enums")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult mvcResult = result.andReturn();

        EnumDocs enumDocs = getData(mvcResult);

        result.andExpect(status().isOk())
                .andDo(restDocs.document(
                        customResponseFields("custom-response", beneathPath("data.yn").withSubsectionId("yn"),
                                attributes(key("title").value("yn")),
                                enumConvertFieldDescriptor((enumDocs.getYn()))
                        ),
                        customResponseFields("custom-response", beneathPath("data.targetGroup").withSubsectionId("targetGroup"),
                                attributes(key("title").value("targetGroup")),
                                enumConvertFieldDescriptor((enumDocs.getTargetGroup()))
                        ),
                        customResponseFields("custom-response", beneathPath("data.categoryType").withSubsectionId("categoryType"),
                                attributes(key("title").value("categoryType")),
                                enumConvertFieldDescriptor((enumDocs.getCategoryType()))
                        )
                ));
    }

    @Test
    public void errorSample() throws Exception {
        CommonDocController.SampleRequest sampleRequest = new CommonDocController.SampleRequest("name","hhh.naver");

        mockMvc.perform(
                        post("/test/error")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleRequest))
                )
                .andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                responseFields(
                                        errorStatusMsg()
                                )
                        )
                )
        ;
    }

    public static CustomResponseFieldsSnippet customResponseFields
            (String type,
             PayloadSubsectionExtractor<?> subsectionExtractor,
             Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes, true);
    }

    private static FieldDescriptor[] enumConvertFieldDescriptor(Map<String, String> enumValues) {
        return enumValues.entrySet().stream()
                .map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
                .toArray(FieldDescriptor[]::new);
    }

    private EnumDocs getData(MvcResult result) throws IOException {
        ApiResponse<EnumDocs> apiResponseDto = objectMapper
                .readValue(result.getResponse().getContentAsByteArray(),
                        new TypeReference<ApiResponse<EnumDocs>>() {}
                );
        return apiResponseDto.getData();
    }

}
