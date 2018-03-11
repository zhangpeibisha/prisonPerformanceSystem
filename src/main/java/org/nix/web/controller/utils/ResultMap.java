package org.nix.web.controller.utils;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 *
 * 组装返回给前端信息的map
 */
public class ResultMap {
    //日志记录
    private static Logger logger = Logger.getLogger(ResultMap.class);

    //返回数据集合的Key
    public static final String DATA = "data";
    //返回分页的总条数
    public static final String TOTAL = "total";


    private Map<String,Object> result = new HashMap<>();

    public Map<String, Object> send() {
        return result;
    }

    public ResultMap appendParameter(String key,Object value) {
        this.result.put(key,value);
        return this;
    }

    public ResultMap resultSuccess(){
        result.put("result","0");
        return this;
    }

    /**
     * 设置基础返回值
     * @param value 返回代码
     * @return
     */
    public ResultMap setResult(String value){
        result.put("result",value);
        return this;
    }
}
