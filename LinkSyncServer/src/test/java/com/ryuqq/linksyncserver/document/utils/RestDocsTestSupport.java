package com.ryuqq.linksyncserver.document.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
@ExtendWith(RestDocumentationExtension.class)
@Import({

        RestDocsConfig.class,

})
public class RestDocsTestSupport extends BaseControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @Autowired
    ResourceLoader resourceLoader;


    protected List<FieldDescriptor> dataFields() {
        return List.of(
                fieldWithPath("data").type(JsonFieldType.NULL).description("null (if there is no response data)"),
                fieldWithPath("response.status").type(JsonFieldType.NUMBER).description("Response status code"),
                fieldWithPath("response.message").type(JsonFieldType.STRING).description("Response message")
        );
    }

    protected List<FieldDescriptor> statusMsg() {
        return List.of(
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("Response status code"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("Response message")
        );
    }

    protected List<FieldDescriptor> errorStatusMsg() {
        return List.of(
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("Response status code"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("Response message"),
                fieldWithPath("timestamp").type(JsonFieldType.STRING).description("Response timestamp"),
                fieldWithPath("error").type(JsonFieldType.STRING).description("Error code")
        );
    }

    @BeforeEach
    void setUp(final WebApplicationContext context, final RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider)
                        .operationPreprocessors()
                        .withRequestDefaults(Preprocessors.prettyPrint())
                        .withResponseDefaults(Preprocessors.prettyPrint()))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocs)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }


}
