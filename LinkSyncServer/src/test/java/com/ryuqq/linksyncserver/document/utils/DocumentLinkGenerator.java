package com.ryuqq.linksyncserver.document.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface DocumentLinkGenerator {

    static String generateLinkCode(DocUrl docUrl) {
        return String.format("link:common/%s.html[%s %s,role=\"popup\"]", docUrl.pageId, docUrl.text, "Code");
    }

    static String generateText(DocUrl docUrl) {
        return String.format("%s %s", docUrl.text, "Code Name");
    }


    @RequiredArgsConstructor
    enum DocUrl {
        CATEGORY_TYPE("categoryType", "Category Type"),
        TARGET_GROUP("targetGroup", "Category Target Group"),
        YN("yn", "Y / N"),
        ;

        private final String pageId;
        @Getter
        private final String text;
    }
}
