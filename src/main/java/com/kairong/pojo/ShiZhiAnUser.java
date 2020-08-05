package com.kairong.pojo;

import lombok.Data;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: 食知安客户信息
 * @date:2020-08-04
 */
@Data
public class ShiZhiAnUser {

    private Integer id;
    // 手机号码
    private String phone;
    // mac地址
    private String mac_address;
    // 微信di
    private String wechat_ssid;
    // 微信名称
    private String wechat_name;
    // 微信头像
    private String wechat_img;

    private String weibo_ssid;
    private String weibo_name;
    private String weibo_img;

    private String create_time;
    private int gender;

    private String birth;
    private double weight;


}
