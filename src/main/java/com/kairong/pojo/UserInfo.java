package com.kairong.pojo;

import lombok.Data;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller.pojo
 * @date:2020/5/18
 */
@Data
public class UserInfo {

    private int id;
    private String name;
    private String email;
    private String pass_word;
    private String create_time;
}
