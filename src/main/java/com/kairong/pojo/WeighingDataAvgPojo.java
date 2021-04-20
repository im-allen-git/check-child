package com.kairong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeighingDataAvgPojo {

    // 自增长id
    private int id;
    // 用户id
    private String user_id;
    // mac
    private String mac;
    // 称名字
    private String name;
    // 类别（盐，油）
    private String item;
    // 日均摄入量
    private String weight_avg;
    // 日均总量
    private String weight_avg_sum;
    // 单位
    private String unit;
    // 创建时间
    private String create_time;
    // 更新时间
    private String update_time;
    // 删除状态 0正常1逻辑删除
    private String del_status;
    // 开始时间
    private String start_time;
    // 结束时间
    private String end_time;
    // 项目中文名
    private String item_value;
    // 单位中文名
    private String unit_value;
    // 周1月2 区分
    private int flag_type;
    private String format_time;
    private String start_week;
    private String end_week;
}
