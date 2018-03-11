package org.nix.domain.entity.dto;

import org.springframework.stereotype.Repository;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 *
 * 用于返回前端所需信息的返回接口
 */
@Repository
public interface ResultDto<T>{

    /**
     * 返回需要的dto组装类
     * @return
     */
    ResultDto resultDto(T t);


}
