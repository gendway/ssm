package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/24 9:56
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /****
     * 根据ID删除数据
     * @return
     */
    @RequestMapping(value = "/delete")
    public String deleteById(Long id){
        //根据ID删除
        int dcount = productService.deleteById(id);

        //列表查询
        return "redirect:/product/list";
    }

    /****
     * 保存修改数据
     * @return
     */
    @RequestMapping(value = "/update")
    public String update(Product product){
        //调用Service实现数据保存
        int mcount = productService.update(product);

        //保存后要跳转到列表查询页面
        return "redirect:/product/list";
    }


    /***
     * 根据ID查询商品信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/one")
    public String getById(Long id,Model model){
        //到后台查询
        Product product = productService.getById(id);

        //将数据存入model
        model.addAttribute("product",product);

        //返回页面product-update.jsp
        return "product-update";
    }



    /***
     * 跳转页面
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "product-add";
    }

    /****
     * 保存Product数据
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Product product){
        //调用Service实现增加
        int acount = productService.add(product);

        //返回到列表页
        return "redirect:/product/list";
    }


    /****
     * PageHelper
     * 产品列表分页实现
     * 分页默认参数设置
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "page",required = false,defaultValue = "1")int page,
                       @RequestParam(value = "size",required = false,defaultValue = "5")int size, Model model){
        //调用service实现查询获取返回结果
        PageInfo<Product> pageInfo = productService.pageHelperList(page,size);

        //将返回结果存储达到model
        model.addAttribute("pageInfo",pageInfo);
        return "product-list";
    }


    /****
     * OracleSQL语句分页实现
     * 产品列表分页实现
     * 分页默认参数设置
     * @return
     */
    @RequestMapping(value = "/listoracle")
    public String listoracle(@RequestParam(value = "page",required = false,defaultValue = "1")int page,
                       @RequestParam(value = "size",required = false,defaultValue = "5")int size, Model model){
        //调用service实现查询获取返回结果
        PageBean<Product> pageInfo = productService.pageList(page,size);

        //将返回结果存储达到model
        model.addAttribute("pageInfo",pageInfo);
        return "product-list";
    }


    /****
     * 产品列表
     * @return
     */
    @RequestMapping(value = "/listall")
    public String listall(Model model){
        //调用service实现查询获取返回结果
        List<Product> products = productService.list();

        //将返回结果存储达到model
        model.addAttribute("products",products);
        return "product-list";
    }

}
