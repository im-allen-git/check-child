package com.kairong.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    // 用户id
    private String user_id;
    // 昵称(默认手机号，第三方登录使用第三方昵称)
    private String nick_name;
    // 手机
    private String mobile;
    // 性别 1:男，2：女
    private String sex;
    // 出生日期
    private String birthday;
    // 身高
    private String height;
    // 体重
    private String weight;
    // 浪费率
    private String waste_rate;
    // 用餐人数
    private String number;
    // 创建时间
    private String create_time;

}
