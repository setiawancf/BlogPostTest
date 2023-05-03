package com.sigma.BlogPostTest.enums;

import com.sigma.BlogPostTest.common.Response;

public enum ResponsCodeTypeEnum {


    SUCCESS(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted "),
    PARAMETER_ERROR(400, "Kesalahan parameter"),
    UNAUTHORIZED(401, "Unauthorized user"),
    FAILURE(999, "Kesalahan tidak dikenal"),
    ;

    private Integer code;
    private String message;

    private ResponsCodeTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    static {
        ResponsCodeTypeEnum[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            ResponsCodeTypeEnum responsCodeTypeEnum = var0[var2];
            Response.RESPONSE_MAP.put(responsCodeTypeEnum.getCode(), responsCodeTypeEnum.getMessage());
        }

    }
}
