package com.itheima.domain;

import com.itheima.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/********
 * author:shenkunlin
 * date:2018/8/24 9:06
 * description:深圳黑马
 * version:1.0
 ******/
public class Product {

    private Long id;
    private String productNum;
    private String productName;
    private String cityName;
    //依赖spring包   spring-context
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date departureTime;
    private Float productPrice;
    private String productDesc;
    private Integer productStatus;

    /****
     * 将Date类型转字符串
     * 但凡在JavaBean中，以get方式存在的方法，都能被页面用EL表达式调用
     *      EL表达式取值：去掉get，将第一个字母小写
     *                  去掉get：DepartureTimeStr
     *                  将第1个字母小写:departureTimeStr
     * @return
     */
    public String getDepartureTimeStr(){
        if(departureTime==null){
            return "";
        }

        //将Date转String
        String str = DateUtil.date2Str1(departureTime);
        return str;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", departureTime=" + departureTime +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                '}';
    }

}
