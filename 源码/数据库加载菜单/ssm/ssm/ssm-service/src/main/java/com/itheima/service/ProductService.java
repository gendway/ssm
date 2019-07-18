package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.util.PageBean;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 9:59
 * description:深圳黑马
 * version:1.0
 ******/
public interface ProductService {

    /****
     * 产品列表查询
     * @return
     */
    List<Product> list();

    /***
     * 增加产品
     * @param product
     * @return
     */
    int add(Product product);

    /***
     * 根据ID查询产品明细
     * @param id
     * @return
     */
    Product getById(Long id);

    /***
     * 修改产品数据
     * @param product
     * @return
     */
    int update(Product product);

    /***
     * 删除数据
     * @param id
     * @return
     */
    int deleteById(Long id);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageBean<Product> pageList(int page, int size);

    /***
     * 使用分页插件实现分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Product> pageHelperList(int page, int size);
}
