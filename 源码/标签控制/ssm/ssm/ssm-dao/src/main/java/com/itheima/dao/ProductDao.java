package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 10:01
 * description:深圳黑马
 * version:1.0
 ******/
public interface ProductDao {

    /***
     * 产品列表查询
     *      select * from product
     *      @Select
     * @return
     */
    @Select(value = "select * from product")
    List<Product> list();

    /***
     * 增加产品信息
     *      @SelectKey[获取序列的值]
     *          statement:编写SqL语句
     *          keyProperty:表示将查询的值赋值给入参的某个属性
     *          resultType:statement执行的SqL语句返回的值的类型
     *          before:执行顺序，是否优先执行
     *
     *
     *      @Insert
     * @param product
     * @return
     */
    @SelectKey(statement = "select product_seq.nextval from dual",keyProperty = "id",resultType=Long.class,before = true)
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    int add(Product product);


    /***
     * 根据ID查询产品信息
     *  @Select
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product getById(Long id);

    /***
     * 修改产品
     * @Upate
     * @param product
     * @return
     */
    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    int update(Product product);

    /***
     * 根据ID删除数据
     * @Delete
     * @param id
     * @return
     */
    @Delete("delete from product where id=#{id}")
    int deleleById(Long id);

    /***
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from product")
    int findCount();

    /****
     * 分页查询
     * @param start
     * @param end
     * @return
     */
    @Select("select * from(select rownum rn,p.* from product p) where rn between #{start} and #{end}")
    List<Product> pageList(@Param(value = "start")int start,@Param(value = "end")int end);
}
