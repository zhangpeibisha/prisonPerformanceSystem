package org.nix.domain.entity.dto;

import org.nix.domain.entity.base.BaseEntity;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 *
 * 用于返回前端所需信息的返回接口
 */
public interface ResultDto<T>{

    //返回一个dto
    BaseEntity resultDto(T t);

}
