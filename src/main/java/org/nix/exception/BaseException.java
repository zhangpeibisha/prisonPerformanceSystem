package org.nix.exception;

import org.apache.log4j.Logger;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 *
 * 基础错误类
 */
public class BaseException extends Exception{
    //日志记录
    private static Logger logger = Logger.getLogger(BaseException.class);

    private String value;
    private String desc;

    public BaseException(Object code) {
        super(code.toString());

        //如果是枚举类就直接赋值
        if (code instanceof LuoErrorCode){
            LuoErrorCode luoErrorCode = (LuoErrorCode) code;
            setValue(luoErrorCode.getValue());
            setDesc(luoErrorCode.getDesc());
        }
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }
}
