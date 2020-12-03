package com.kairong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeighingdataPojo {


    // 自增长id
    private int id;
    // 用户id
    private String user_id;
    // mac
    private String mac;
    // 左右称名字
    private String name;
    // 类别（盐，油）
    private String item;
    // 类型 (1手动输入，2设备数据)
    private String type;
    // 称重数
    private String weight;
    // 单位
    private String unit;
    // 创建时间
    private String create_time;
    // 浪费比率
    private String waste_rate;
    // 进餐人数
    private int number;
    // 更新时间
    private String update_time;
    // 删除状态 0正常1逻辑删除
    private String del_status;
    // 重量集合数据（id:weight）
    private String weightStr;
    // id集合数据（id:id）
    private String idAllStr;
    // 开始时间
    private String start_time;
    // 结束时间
    private String end_time;

    // 周月年 区分
    private int flag_type;
    private String format_time;
    private String group_weight;
    private String avg_weight;

    private String start_week;
    private String end_week;
    // 项目中文名
    private String item_value;
    // 单位中文名
    private String unit_value;


}
