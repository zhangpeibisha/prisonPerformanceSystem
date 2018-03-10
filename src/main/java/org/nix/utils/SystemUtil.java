package org.nix.utils;

import org.apache.log4j.Logger;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
public class SystemUtil {
    //日志记录
    private static Logger logger = Logger.getLogger(SystemUtil.class);

    private SystemUtil() {
    }

    /**
     *判断参数是否为空
     * @param value 需要判断的参数
     * @return 如果有空参数则返回true
     */
    public static boolean parameterNull(Object... value) {
        for (Object object : value) {
            if (object == null){
                return true;
            }
        }
        return false;
    }
}
