package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 12:09
 * description:深圳黑马
 * version:1.0
 ******/
public interface OrdersService {

    /***
     * 查询订单信息
     * @return
     */
    List<Orders> list();

    /***
     * 增加订单
     * @param orders
     * @return
     */
    int add(Orders orders);
}
