package com.itheima.controller;

import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import com.itheima.service.OrdersService;
import com.itheima.service.ProductService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 12:07
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    /***
     * 保存方法
     * @param orders
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Orders orders){
        //增加操作
        int acount = ordersService.add(orders);

        //列表显示
        return "redirect:/orders/list";
    }

    /****
     * 实现增加页面跳转
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        //查询产品信息，到页面显示
        List<Product> products = productService.list();

        //存入Model
        model.addAttribute("products",products);
        return "order-add";
    }



    /***
     * 列表查询
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model){
        //调用Service，获取结果集
        List<Orders> orders = ordersService.list();

        //将结果集存入model
        model.addAttribute("orders",orders);

        //回显页面
        return "order-list";
    }


}
