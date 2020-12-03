package com.kairong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentPojo {

    // mac地址
    private String mac;
//    // 设备编号
//    private String uuid;
    // 称左右名字
    private String name;
    // 用户id
    private String user_id;
    // 绑定内容(盐、糖或其他自定义)
    private String item;
    // 称重单位
    private String unit;
    // 推荐指标
    private String target;
    // 创建时间
    private String create_time;
    // 更新时间
    private String update_time;
    // ip地址
    private String ip_address;
    // 1：蓝牙，2：wifi
    private String online_type;
    // 设备服务id
    private String service_id;
    // 设备服务特征id
    private String characteristic_id;
    // 设备名
    private String device_name;
    // 要变换称的名字
    private String set_name;
    // 要变换称的项目
    private String set_item;
    // 项目中文名
    private String item_value;
    // 单位中文名
    private String unit_value;
    // 在线名称 蓝牙 wifi
    private String online_value;

}
