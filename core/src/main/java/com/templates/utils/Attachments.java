package com.templates.utils;

import com.templates.enums.AttachmentFormat;
import io.qameta.allure.Attachment;

class Attachments {

    @Attachment(type = "text/plain", value = "{attachmentName}")
    private String _takeAttachmentTEXT(String attachmentName, String attachment) {

        return attachment;
    }

    @Attachment(type = "text/plain", value = "{attachmentName}")
    private byte[] _takeAttachmentTEXT(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/xml", value = "{attachmentName}")
    private byte[] _takeAttachmentXML(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/xls", value = "{attachmentName}")
    private byte[] _takeAttachmentXLS(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/csv", value = "{attachmentName}")
    private byte[] _takeAttachmentCSV(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/pdf", value = "{attachmentName}")
    private byte[] _takeAttachmentPDF(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/html", value = "{attachmentName}")
    private byte[] _takeAttachmentHTML(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/rtf", value = "{attachmentName}")
    private byte[] _takeAttachmentRTF(String attachmentName, byte[] attachment) {

        return attachment;
    }

    @Attachment(type = "application/xml", value = "{attachmentName}")
    private String _takeAttachmentXML(String attachmentName, String attachment) {

        return attachment;
    }

    @Attachment(type = "application/xls", value = "{attachmentName}")
    private String _takeAttachmentXLS(String attachmentName, String attachment) {

        return attachment;
    }

    @Attachment(type = "application/csv", value = "{attachmentName}")
    private String _takeAttachmentCSV(String attachmentName, String attachment) {

        return attachment;
    }

    @Attachment(type = "application/html", value = "{attachmentName}", fileExtension = "html")
    private String _takeAttachmentHTML(String attachmentName, String attachment) {

        return attachment;
    }

    @Attachment(type = "application/rtf", value = "{attachmentName}")
    private String _takeAttachmentRTF(String attachmentName, String attachment) {

        return attachment;
    }

    void takeAttachment(AttachmentFormat format, Object attachment, String attachmentName) {

        switch (format) {
            case CSV:
                if (attachment instanceof byte[])
                    _takeAttachmentCSV(attachmentName, (byte[]) attachment);
                else
                    _takeAttachmentCSV(attachmentName, (String) attachment);
                break;
            case HTML:
                if (attachment instanceof byte[])
                    _takeAttachmentHTML(attachmentName, (byte[]) attachment);
                else
                    _takeAttachmentHTML(attachmentName, (String) attachment);
                break;
            case PDF:
                _takeAttachmentPDF(attachmentName, (byte[]) attachment);
                break;
            case RTF:
                if (attachment instanceof byte[])
                    _takeAttachmentRTF(attachmentName, (byte[]) attachment);
                else
                    _takeAttachmentRTF(attachmentName, (String) attachment);
                break;
            case XLS:
                if (attachment instanceof byte[])
                    _takeAttachmentXLS(attachmentName, (byte[]) attachment);
                else
                    _takeAttachmentXLS(attachmentName, (String) attachment);
                break;
            case XML:
                if (attachment instanceof byte[])
                    _takeAttachmentXML(attachmentName, (byte[]) attachment);
                else
                    _takeAttachmentXML(attachmentName, (String) attachment);
                break;
            case TEXT:
                if (attachment instanceof byte[])
                    _takeAttachmentTEXT(attachmentName, (byte[]) attachment);
                else
                    _takeAttachmentTEXT(attachmentName, (String) attachment);
                break;
            case TXT:
                _takeAttachmentTEXT(attachmentName, (String) attachment);
        }
    }
}
