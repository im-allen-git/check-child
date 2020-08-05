package com.kairong.mapper;

import com.kairong.pojo.UserInfo;
import com.kairong.pojo.UserPojo;
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


    @Select("select count(0) from user_saz where mobile = #{mobile}")
    int checkUserIdExist(String mobile);

    @Insert("insert into user_saz(nick_name,mobile) values(#{nickName},#{mobile}})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveUserDataBase(String nickName,String mobile);

    @Select("select user_id,nick_name,mobile,sex,birthday,height,weight,waste_rate,number,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%S') as create_time " +
            "from user_saz where user_id = #{userId}")
    UserPojo getUserInfoData(String userId);

    @Update("<script> update user_saz set " +
            "<if test='nick_name!=null '> nick_name = #{nick_name}, </if>" +
            "<if test='sex!=null '> sex = #{sex}, </if>" +
            "<if test='birthday!=null '> birthday = #{birthday}, </if>" +
            "<if test='height!=null '> height = #{height}, </if>" +
            "<if test='weight!=null '> weight = #{weight}, </if>" +
            "<if test='waste_rate!=null '> waste_rate = #{waste_rate}, </if>" +
            "<if test='number!=null '> number = #{number}, </if>" +
            " where user_id = #{user_id} </script>")
    int updateUserInfoDataBase(UserPojo userPojo);
}
