package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/********
 * author:shenkunlin
 * date:2018/8/29 10:49
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/pages")
public class PageController {

    /****
     * 通用页面跳转
     * @param pageName
     * @return
     */
    @RequestMapping(value = "/{pagename}")
    public String page(@PathVariable(value = "pagename")String pageName){
        return pageName;
    }
}
