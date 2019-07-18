package com.itheima.dao;

import com.itheima.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 12:11
 * description:深圳黑马
 * version:1.0
 ******/
public interface OrdersDao {


    /****
     * 编写Sql语句实现查询
     * @Select
     * @return
     */
    @Results(
            value ={
            @Result(property = "id",column = "oid"),
            @Result(property = "product.id",column = "id"),
            @Result(property = "product.productNum",column = "productNum"),
            @Result(property = "product.productName",column = "productName"),
            @Result(property = "product.cityName",column = "cityName"),
            @Result(property = "product.departureTime",column = "departureTime"),
            @Result(property = "product.productPrice",column = "productPrice"),
            @Result(property = "product.productDesc",column = "productDesc"),
            @Result(property = "product.productStatus",column = "productStatus")
        }
    )
    @Select(value = "select p.*,o.id oid,o.ordernum,o.ordertime,o.peoplecount,o.orderdesc,o.paytype,o.orderstatus from product p,orders o where p.id=o.productid")
    List<Orders> list();


    /***
     * 增加操作
     * @param orders
     * @return
     */
    @SelectKey(statement = "select orders_seq.nextval from dual",before = true,resultType = Long.class,keyProperty="id")
    @Insert("insert into orders(id,orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,productId)values(#{id},#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{product.id})")
    int add(Orders orders);
}
