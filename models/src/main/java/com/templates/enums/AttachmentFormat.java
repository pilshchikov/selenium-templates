package com.templates.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentFormat {

    XML("application/xml"),
    XLS("application/xls"),
    CSV("application/csv"),
    PDF("application/pdf"),
    TXT("plain/text"),
    TEXT("plain/text"),
    HTML("application/html"),
    RTF("application/rtf");

    private final String attachmentFormat;
}
