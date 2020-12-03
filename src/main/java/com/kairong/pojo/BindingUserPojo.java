package com.kairong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindingUserPojo {

    // 用户id
    private String user_id;
    // 绑定userid
    private String binding_userid;
    // 创建时间
    private String create_time;
    // 更新时间
    private String update_time;
    // 类型 1手机2二维码
    private String type;
    // 名字
    private String nick_name;

}
