package org.nix.exception;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 *
 * 设置错误异常枚举类
 */
public enum LuoErrorCode {

    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","添加用户失败"),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试...."),
    USERNAME_PASSWORD_ERROR("LUO004","账号或者密码错误"),
    PARAMETER_NULL("LUO005","空指针异常"),
    CONSTRAINT_VIOLATE_DATABASES("LUO006","插入重复"),
    NULL_FIEID_DATABASES("LUO007","插入字段为空"),
    PERMISSION_DENIED("LUO003","访问拒绝，没有权限");

    private String value;
    private String desc;

    LuoErrorCode(String value, String desc) {
        this.setValue(value);
        this.setDesc(desc);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + this.value + "]" + this.desc;
    }
}
