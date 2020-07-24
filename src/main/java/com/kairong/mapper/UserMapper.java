package com.kairong.mapper;

import com.kairong.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.mapper
 * @date:2020/5/18
 */
@Mapper
@Component
public interface UserMapper {


    @Select("select id,name,email,pass_word,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%S') as create_time from android_user where email = #{email}")
    UserInfo queryByEmail(UserInfo userInfo);

    @Select("select count(0) from android_user where email = #{email}")
    int checkEmail(UserInfo userInfo);

    @Insert("insert into android_user(email,pass_word) values(#{email},#{pass_word})")
    int insertUserInfo(UserInfo userInfo);

    @Update("update android_user set pass_word = #{pass_word} where id = #{id}")
    int updateUserInfo(UserInfo userInfo);

    @Update("update equipment set ip_address = #{ip},online_type=2 where mac = #{mac}")
    int equipmentInfoUp(String ip,String mac);

    @Select("select user_id from equipment where mac = #{mac}")
    int getUserId(String mac);

    @Insert("insert into weighing_data(user_id,mac,item,type,weight,create_time) values(#{userId},#{mac},#{item},1,#{weight},#{createTime})")
    int weighingDataInsert(String userId,String mac,String item,String weight,String createTime);



}
