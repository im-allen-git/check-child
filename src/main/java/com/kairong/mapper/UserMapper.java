package com.kairong.mapper;

import com.kairong.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.mapper
 * @date:2020/5/18
 */
@Mapper
@Component
public interface UserMapper {


//    @Select("select id,name,email,pass_word,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%S') as create_time from android_user where email = #{email}")
//    UserInfo queryByEmail(UserInfo userInfo);
//
//    @Select("select count(0) from android_user where email = #{email}")
//    int checkEmail(UserInfo userInfo);
//
//    @Insert("insert into android_user(email,pass_word) values(#{email},#{pass_word})")
//    int insertUserInfo(UserInfo userInfo);
//
//    @Update("update android_user set pass_word = #{pass_word} where id = #{id}")
//    int updateUserInfo(UserInfo userInfo);

    @Update("update equipment set ip_address = #{ip_address},online_type=#{online_type} where mac = #{mac}")
    int equipmentInfoUp(EquipmentPojo equipmentPojo);

    @Select("select user_id from equipment where mac = #{mac} limit 1")
    int getUserId(String mac);

    @Insert("insert into weighing_data(user_id,mac,item,type,weight,create_time) values(#{userId},#{mac},#{item},1,#{weight},#{createTime})")
    int weighingDataInsert(String userId,String mac,String item,String weight,String createTime);


    @Select("select user_id from user_saz where mobile = #{mobile}")
    UserPojo checkUserIdExist(UserPojo userPojo);

    @Insert("insert into user_saz(nick_name,mobile,type) values(#{nick_name},#{mobile},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    int saveUserDataBase(UserPojo userPojo);

    @Select("select a.user_id,a.nick_name,a.mobile,a.sex,a.birthday,a.height,a.weight,a.waste_rate,a.number,DATE_FORMAT(a.create_time,'%Y-%m-%d %H:%i:%S') as create_time,b.online_type,a.type " +
            "from user_saz a LEFT JOIN equipment b on a.user_id = b.user_id where a.user_id = #{user_id} limit 1 ")
    UserPojo getUserInfoData(UserPojo userPojo);

    @Update("<script> update user_saz set " +
            "<if test='nick_name!=null and nick_name != &quot;&quot; '> nick_name = #{nick_name}, </if>" +
            "<if test='sex!=null and sex != &quot;&quot; '> sex = #{sex}, </if>" +
            "<if test='birthday!=null and birthday != &quot;&quot; '> birthday = #{birthday}, </if>" +
            "<if test='height!=null and height != &quot;&quot; '> height = #{height}, </if>" +
            "<if test='weight!=null and weight != &quot;&quot; '> weight = #{weight}, </if>" +
            "<if test='waste_rate!=null and waste_rate != &quot;&quot;  '> waste_rate = #{waste_rate}, </if>" +
            "<if test='number!=null and number != &quot;&quot;'> number = #{number}, </if>" +
            "<if test='type!=null and type != &quot;&quot;'> number = #{type}, </if>" +
            "  update_time= now() where user_id = #{user_id} </script>")
    int updateUserInfoDataBase(UserPojo userPojo);

    @Select("select count(0) from binding_user where user_id = #{user_id} and binding_userid = #{binding_userid}")
    int checkbingIdExist(BindingUserPojo bindingUserPojo);

    @Insert("insert into binding_user(user_id,binding_userid) values(#{user_id},#{binding_userid})")
    int saveBindingUserDataBase(BindingUserPojo bindingUserPojo);

    @Delete("delete from binding_user where user_id = #{user_id} and binding_userid = #{binding_userid}")
    int bindingUserDel(BindingUserPojo bindingUserPojo);

    @Insert("insert into equipment(mac,name,user_id,item,unit,target,ip_address,online_type,service_id,characteristic_id,device_name) " +
            "values(#{mac},#{name},#{user_id},#{item},#{unit},#{target},#{ip_address},#{online_type},#{service_id},#{characteristic_id},#{device_name})")
    int saveEquipmentDataBase(EquipmentPojo equipmentPojo);

    @Select("select count(0) from equipment where user_id = #{user_id} and mac = #{mac}  and item = #{item}  and name = #{name} ")
    int checkEquipmentDataBase(EquipmentPojo equipmentPojo);

    @Delete("<script> delete from equipment where mac = #{mac} and user_id = #{user_id} " +
            " <if test='item!=null and item != &quot;&quot; '> and item = #{item} </if> </script> " )
    int equipmentDel(EquipmentPojo equipmentPojo);

    @Select("select user_id,binding_userid from binding_user where user_id = #{user_id}")
    List<BindingUserPojo> getBindingUserList(BindingUserPojo bindingUserPojo);

    @Update("<script> update equipment set " +
            "<if test='item!=null and item != &quot;&quot; '> item = #{item}, </if>" +
            "<if test='unit!=null and unit != &quot;&quot; '> unit = #{unit}, </if>" +
            "<if test='target!=null and target != &quot;&quot; '> target = #{target}, </if>" +
            "<if test='online_type!=null and online_type != &quot;&quot; '> online_type = #{online_type}, </if>" +
            "<if test='ip_address!=null and ip_address != &quot;&quot; '> ip_address = #{ip_address}, </if>" +
            "  update_time= now() where user_id = #{user_id} " +
            " <if test='item!=null and item != &quot;&quot; '> and item = #{item} </if> " +
            " <if test='mac!=null and mac != &quot;&quot; '> and mac = #{mac}   </if> </script>")
    int updateEquipment(EquipmentPojo equipmentPojo);


    @Select("<script> select mac,name,user_id,item,unit,target,ip_address,online_type,service_id,characteristic_id,device_name,update_time from equipment where user_id = #{user_id} " +
            "<if test='mac!=null and mac != &quot;&quot; '> and mac = #{mac} </if> </script>" )
    List<EquipmentPojo> getEquipmentDataList(EquipmentPojo equipmentPojo);


    @Insert("insert into weighing_data(user_id,mac,name,item,type,weight,unit,waste_rate,number,create_time) " +
            "values(#{user_id},#{mac},#{name},#{item},#{type},#{weight},#{unit},#{waste_rate},#{number},#{create_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int weighingdataAdd(WeighingdataPojo weighingdataPojo);

    @Update("<script> update weighing_data set " +
            "<if test='type!=null and type != &quot;&quot; '> type = #{type}, </if>" +
            "<if test='waste_rate!=null and waste_rate != &quot;&quot; '> waste_rate = #{waste_rate}, </if>" +
            "<if test='number!=null and number != &quot;&quot; '> number = #{number}, </if>" +
            "<if test='weight!=null and weight != &quot;&quot; '> weight = #{weight}, </if>" +
            "  update_time= now() where id = #{id} and user_id = #{user_id} </script>")
    int updateWeighingdata(WeighingdataPojo weighingdataPojo);

    @Update("<script> update weighing_data set " +
            "  del_status=1 ,update_time= now() where id = #{id} and user_id = #{user_id} </script>")
    int updateDelWeighingData(WeighingdataPojo weighingdataPojo);




    @Select("<script> select id,user_id,mac,name,item,type,weight,unit,waste_rate,number,create_time,del_status " +
            " from weighing_data where user_id = #{user_id} " +
            "<if test='mac!=null and mac != &quot;&quot; '> and mac = #{mac} </if>" +
            "<if test='item!=null and item != &quot;&quot; '> and item = #{item} </if>" +
            "<if test='type!=null and type != &quot;&quot; '> and type = #{type} </if> " +
            "<if test='start_time!=null and start_time != &quot;&quot; '> and create_time &gt;= #{start_time} </if>" +
            "<if test='end_time!=null and end_time != &quot;&quot; '> and create_time &lt;= #{end_time} </if>" +
            " order by create_time desc </script>")
    List<WeighingdataPojo> getWeightingDataList(WeighingdataPojo weighingdataPojo);

    @Select("<script> select DATE_FORMAT(create_time,'%Y-%m-%d %H:%i') format_time,sum(weight) as group_weight,sum(weight)/sum(number) as avg_weight,type " +
            " from weighing_data where user_id = #{user_id} " +
            "<if test='item!=null and item != &quot;&quot; '> and item = #{item} </if>" +
            "<if test='type!=null and type != &quot;&quot; '> and type = #{type} </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; '> and create_time &gt;= #{start_time} </if>" +
            "<if test='end_time!=null and end_time != &quot;&quot; '> and create_time &lt;= #{end_time} </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; '>  GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d %H') ORDER BY type desc ,create_time </if></script>")
    List<WeighingdataPojo> getWeightingDataCalculateList(WeighingdataPojo weighingdataPojo);


    @Select("<script> select id,user_id,mac,item,type,weight,unit,create_time,waste_rate,number,update_time,del_status " +
            " from weighing_data where user_id = #{user_id} " +
            "<if test='item!=null and item != &quot;&quot; '> and item = #{item} </if>" +
            "<if test='type!=null and type != &quot;&quot; '> and type = #{type} </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; '> and create_time &gt;= #{start_time} </if>" +
            "<if test='end_time!=null and end_time != &quot;&quot; '> and create_time &lt;= #{end_time} </if>" +
            "  </script>")
    List<WeighingdataPojo> getWeightingDataListByHour(WeighingdataPojo weighingdataPojo);


    @Select("<script> select sum(weight) as weight " +
            "<if test='flag_type==1 '>  ,DATE_FORMAT(create_time,'%Y-%m-%d') AS format_time  </if>" +
            "<if test='flag_type==2 '>  ,WEEK(create_time) as format_time,min(create_time) as start_week,max(create_time) as end_week  </if>" +
            "<if test='flag_type==3 '>  ,MONTH(create_time) as format_time  </if>" +
            "<if test='flag_type==4 '>  ,YEAR(create_time) as format_time  </if>" +
            " from weighing_data where user_id = #{user_id} " +
            "<if test='item!=null and item != &quot;&quot; '> and item = #{item} </if>" +
            "<if test='type!=null and type != &quot;&quot; '> and type = #{type} </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; '> and create_time &gt;= #{start_time} </if>" +
            "<if test='end_time!=null and end_time != &quot;&quot; '> and create_time &lt;= #{end_time}  </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; and flag_type==1 '>  GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; and flag_type==2 '>  GROUP BY WEEK(create_time) </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; and flag_type==3 '>  GROUP BY MONTH(create_time) </if>" +
            "<if test='start_time!=null and start_time != &quot;&quot; and flag_type==4 '>  GROUP BY YEAR(create_time) </if>" +
            "</script>")
    List<WeighingdataPojo> getWeightingDataListByYMWD(WeighingdataPojo weighingdataPojo);



}
