package com.zhonggu.monitor;

/**
 * Created by lixiaoming on 2017/7/10.
 */
public enum RespCode {
    SUCCESS("000000","交易成功"),
    EXCEPTION("299999","服务器内部错误"),
    FAIL("100000","交易失败");

    private String code;
    private String name;

    private RespCode(String code, String name)
    {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
