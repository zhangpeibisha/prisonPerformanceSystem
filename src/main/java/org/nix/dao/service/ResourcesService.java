package org.nix.dao.service;

import org.apache.log4j.Logger;
import org.nix.dao.base.SupperBaseDAOImp;
import org.nix.domain.entity.Resources;
import org.nix.domain.entity.entitybuild.ResourcesBuild;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * Create by zhangpe0312@qq.com on 2018/3/10.
 */
@Service
@Transactional
public class ResourcesService extends SupperBaseDAOImp<Resources> {
    //日志记录
    private static Logger logger = Logger.getLogger(ResourcesService.class);

    @Override
    public <T> List<T> findByCriteria(T object, Integer startRow, Integer pageSize) {
        return null;
    }

    @Override
    public <T> Long findByCriteriaCount(T object) {
        return null;
    }

    /**
     * 将control的URL路径存入数据库中
     * @param resources
     */
    public void batchSaveResources(Set<String> resources){

        List<Resources> resources1 = new ArrayList<>();

        Iterator iterator  =  resources.iterator();

        while(iterator.hasNext()){
            String value = (String) iterator.next();

            String name = value.substring(value.indexOf("/"));

            Resources resources2 = new ResourcesBuild()
                    .setCreateTime()
                    .setName(name)
                    .setUrl(value)
                    .build();

            resources1.add(resources2);

        }

        batchInsertByHQL(resources1,resources.size());

        logger.info("获取所有URL并存入数据库中");

    }
}
