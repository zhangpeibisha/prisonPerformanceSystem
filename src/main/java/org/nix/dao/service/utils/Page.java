package org.nix.dao.service.utils;

import org.apache.log4j.Logger;
import org.nix.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangpe0312@qq.com on 2018/3/11.
 * <p>
 * 用于分页查询
 */
public class Page<T> {
    //日志记录
    private static Logger logger = Logger.getLogger(Page.class);

    //需要导入的数据
    private List<T> list; //查询的总数据

    //计算得到的数据
    private int total;//查询总条数

    private int limit = 10;//每页条数

    private int currentPage = 1;//当前页

    /**
     * 获取指定页的信息集合
     *
     * @param limit       每页条数
     * @param currentPage 当前页
     * @return 数据集合
     */
    public List<T> getPageList(int limit, int currentPage) {

        if (SystemUtil.parameterNull(list)) {
            return null;
        }

        //获取开始坐标
        int indexStart = (limit - 1) * currentPage;
        //获取结束坐标
        int indexEnd = indexStart + limit;

        //如果开始坐标大于数量则返回空
        if (indexStart>getTotal()){
            return null;
        }

        //如果尾坐标大于数组长度，则尾坐标设置为数组数量
        if (indexEnd>getTotal()){
            indexEnd = getTotal();
        }

        List<T> temp = new ArrayList<>();
        //获取指定坐标的值
        for (int i = indexStart; i < indexEnd; i++) {
            temp.add(list.get(indexStart));
        }

        return temp;
    }

    public List<T> getList() {
        return list;
    }

    public Page setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int getTotal() {
        return list.size();
    }

    public int getLimit() {
        return limit;
    }

    public Page setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Page setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }
}
