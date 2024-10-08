package com.ryuqq.linksyncserver.document;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumDocs {

    //site
    Map<String, String> yn;

    //category
    Map<String, String> categoryType;
    Map<String, String> targetGroup;




}
