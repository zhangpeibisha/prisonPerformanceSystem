package org.nix.exception;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 *
 * 设置错误异常枚举类
 */
public enum ErrorCode {

    USERNAME_PASSWORD_ERROR("ERROR004","账号或者密码错误"),
    PARAMETER_NULL("ERROR005","空指针异常"),
    CONSTRAINT_VIOLATE_DATABASES("ERROR006","Insert或Update数据时违反了完整性，例如违反了惟一性限制"),
    NULL_FIEID_DATABASES("ERROR007","插入字段为空"),
    IDENTITY_OVERDUE("ERROR008","身份过期"),
    PERMISSION_DENIED("ERROR003","访问拒绝，没有权限"),
    DATABASES_ERROR_UNCATEGORIZED("ERROR009","数据库发生错误，无法归类"),
    DATABASES_ERROR_DATA_RETRIEVAL_FAIL("ERROR011","某些数据不能被检测到，例如不能通过关键字找到一条记录");

    private String value;
    private String desc;

    ErrorCode(String value, String desc) {
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
