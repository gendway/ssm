package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 9:59
 * description:深圳黑马
 * version:1.0
 ******/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> list() {
        return productDao.list();
    }

    @Override
    public int add(Product product) {
        return productDao.add(product);
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public int update(Product product) {
        return productDao.update(product);
    }

    @Override
    public int deleteById(Long id) {
        return productDao.deleleById(id);
    }

    @Override
    public PageBean<Product> pageList(int page, int size) {
        //分页计算
        PageBean<Product> pageInfo = new PageBean<Product>();

        //1、查询总记录数
        int count = productDao.findCount();
        pageInfo.setTotalCount(count);

        //2、查询集合数据
        List<Product> products = productDao.pageList((page-1)*size+1,page*size);
        pageInfo.setBeanList(products);

        return pageInfo;
    }

    /***
     * 使用PageHelper实现分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Product> pageHelperList(int page, int size) {
        //1、调用静态方法,第1个参数：当前页，第2个参数：每页显示多少行
        PageHelper.startPage(page,size);

        //2、集合查询[不需要做分页]
        List<Product> products = productDao.list();

        //3、创建PageInfo
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        return pageInfo;
    }
}
