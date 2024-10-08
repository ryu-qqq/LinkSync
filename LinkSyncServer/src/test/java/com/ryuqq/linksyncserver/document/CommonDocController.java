package com.ryuqq.linksyncserver.document;

import com.ryuqq.linksyncserver.module.category.enums.CategoryType;
import com.ryuqq.linksyncserver.module.category.enums.TargetGroup;
import com.ryuqq.linksyncserver.module.generic.EnumType;
import com.ryuqq.linksyncserver.module.generic.enums.Yn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class CommonDocController {

    @GetMapping("/enums")
    public ApiResponse<EnumDocs> findEnums() {
        Map<String, String> yn = getDocs(Yn.values());
        Map<String, String> targetGroup = getDocs(TargetGroup.values());
        Map<String, String> categoryType = getDocs(CategoryType.values());

        EnumDocs build = EnumDocs.builder()
                .yn(yn)
                .targetGroup(targetGroup)
                .categoryType(categoryType)
                .build();

        return ApiResponse.of(build);
    }


    @PostMapping("/error")
    public void errorSample(@RequestBody @Validated SampleRequest sampleRequest) {
    }


    private Map<String, String> getDocs(EnumType[] enumTypes) {
        return Arrays.stream(enumTypes)
                .collect(Collectors.toMap(EnumType::getName, EnumType::getDescription));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SampleRequest {

        @NotEmpty
        private String name;
        @Email
        private String email;
    }
}
