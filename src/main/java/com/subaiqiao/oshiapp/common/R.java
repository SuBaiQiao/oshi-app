package com.subaiqiao.oshiapp.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private Object data;

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 快速成功
    public static R ok() {
        return new R(200, "ok");
    }

    // 快速成功
    public static R ok(Object data) {
        return new R(200, "ok", data);
    }

    // 快速失败
    public static R error() {
        return new R(500, "error");
    }

    // 快速失败
    public static R error(Object data) {
        return new R(500, "error", data);
    }

    // 快速失败
    public static R error(Integer code, String msg) {
        return new R(code, msg);
    }
}
