package com.kairong.mapper;

import com.kairong.pojo.ShiZhiAnUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.mapper
 * @date:2020/5/18
 */
@Mapper
@Component
public interface ShiZhiAnMapper {

    @Insert("insert into shizhian_user(phone,mac_address,wechat_ssid,wechat_name,wechat_img,weibo_ssid," +
            "weibo_name,weibo_img,gender,birth,weight) values(#{phone},#{mac_address},#{wechat_ssid}," +
            "#{wechat_name},#{wechat_img},#{weibo_ssid},#{weibo_name},#{weibo_img},#{gender},#{birth},#{weight})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(ShiZhiAnUser user);

    @Update("update shizhian_user set phone = #{phone} where id = #{id}")
    int updateUser(ShiZhiAnUser user);


    @Select("select count(0) from shizhian_user where phone = #{phone} or mac_address = #{mac_address} or wechat_ssid = #{wechat_ssid} or weibo_ssid = #{weibo_ssid}")
    int checkUser(ShiZhiAnUser user);

    @Select("select id,mac_address,phone,wechat_ssid,wechat_name,wechat_img,weibo_ssid,weibo_name,weibo_img,gender,birth,weight,create_time,update_time from shizhian_user where phone = #{phone}")
    ShiZhiAnUser selectUser(ShiZhiAnUser user);

}
