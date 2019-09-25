package com.bupt.ZigbeeResolution.mapper;

import com.bupt.ZigbeeResolution.controller.InfraredController;
import com.bupt.ZigbeeResolution.data.InfraredKey;
import com.bupt.ZigbeeResolution.data.InfraredPanel;
import com.bupt.ZigbeeResolution.data.Learn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InfraredMapper {
    @Select("SELECT `key` FROM infrared_model WHERE deviceId = #{deviceId} AND type = #{matchType} AND brand_cn = #{brand_cn} AND name = #{name}")
    Integer select_by_deviceid_type_brandCN_name(String deviceId, Integer matchType, String brand_cn, String name);

    //下发学习指令时插入的数据，添加了customerId、buttonId、panelId、state
    @Insert("INSERT INTO infrared_model(deviceId, `key`, name, type, customerId, buttonId, panelId, status) VALUES(#{deviceId}, #{key}, #{name}, #{matchType}, #{customerId}, #{buttonId}, #{panelId}, #{state})")
    void insert(@Param("deviceId") String deviceId, @Param("key") Integer key, @Param("name") String name, @Param("matchType") Integer matchType,
                @Param("customerId") Integer customerId, @Param("buttonId") Integer buttonId, @Param("panelId") Integer panelId, @Param("state") Integer state);

    //空调设备功能键编号必须大于十进制 602
    @Select("SELECT MAX(`key`) FROM infrared_model WHERE `key` > 602 AND deviceId = #{deviceId} AND type = 1")
    Integer select_maxnum_of_airCondition(String deviceId);

    //非空调设备功能键编号必须大于十进制 43
    @Select("SELECT MAX(`key`) FROM infrared_model WHERE deviceId = #{deviceId} AND `key` > 43 AND type != 1")
    Integer select_maxnum_of_non_airCondition(String deviceId);


    @Select("SELECT `key` FROM infrared_model WHERE `key` = #{key} AND deviceId = #{deviceId}")
    Integer select_by_key_deviceId(@Param("deviceId") String deviceId, @Param("key") Integer key);

    //删除某个学习好的红外设备的数据
    @Delete("DELETE FROM infrared_model WHERE `key` = #{key} AND deviceId = #{deviceId}")
    void delete_a_key(@Param("deviceId") String deviceId, @Param("key") Integer key);

    //删除红外设备的全部学习键
    @Delete("DELETE FROM infrared_model WHERE deviceId = #{deviceId}")
    void delete_all_key(String deviceId);

    //把学习成功的学习码状态修改为 1
    @Update("update infrared_model set state = 1 where `key` = #{key} AND deviceId = #{deviceId}")
    void updateStateByResult(@Param("deviceId") String deviceId, @Param("key") Integer key);

    //获取该用户所学习的所有功能
    @Select("select name,panelName from infrared_model,panel where deviceId = #{deviceId}" +
            "and customerId = #{customerId} and state=1 and infrared_model.panelId=panel.panelId ")
    List<Learn> selectCustomerLearn(@Param("deviceId") String deviceId, @Param("customerId") Integer customerId);

    //获取该用户某个遥控面板已学习的功能
    @Select("select name from infrared_model where deviceId = #{deviceId} and customerId = #{customerId} " +
            "and panelId = #{panelId} and state=1  ")
    List<String> selectKeyNames(@Param("deviceId") String deviceId, @Param("customerId") Integer customerId, @Param("panelId") Integer panelId);

    //获取该红外设备某个遥控面板已学习的功能
    @Select("select name from infrared_model where deviceId = #{deviceId} and panelId = #{panelId} and state=1  ")
    List<String> selectDevicelearns(@Param("deviceId") String deviceId, @Param("panelId") Integer panelId);

    //获取该红外设备所学习的所有功能
    @Select("select name,panelName from infrared_model,panel where deviceId = #{deviceId} and state=1 and infrared_model.panelId=panel.panelId ")
    List<Learn> selectAllLearns(@Param("deviceId") String deviceId);


    // =========================================================================================

    @Select("SELECT * FROM infrared_panel WHERE deviceId = #{deviceId}")
    List<InfraredPanel> select_all_panels(@Param("deviceId")String devieId);

    @Select("SELECT * FROM infrared_panel WHERE deviceId = #{deviceId} AND panelId = #{panelId}")
    InfraredPanel select_panel(@Param("deviceId")String deviceId, @Param("panelId")Integer panelId);

    @Delete("DELETE FROM infrared_panel WHERE deviceId = #{deviceId}")
    int delete_all_panels(@Param("deviceId")String deviceId);

    @Delete("DELETE FROM infrared_panel WHERE deviceId = #{deviceId} AND panelId = #{panelId}")
    int delete_panel(@Param("deviceId")String deviceId, @Param("panelId")Integer panelId);

    @Insert("INSERT INTO infrared_panel(deviceId, panelId, name, type) " +
            "VALUES(#{deviceId},#{panelId}, #{name}, #{type}")
    int insert_panel(InfraredPanel panel);

    @Update("UPDATE infrared_panel set name = #{name} AND type = #{tupe}" +
            "WHERE deviceId = #{deviceId} AND panelId = #{panelId}")
    int update_panel(InfraredPanel panel);

    @Select("SELECT * FROM infrared_key WHERE panelId = #{panelId}")
    List<InfraredKey> select_all_keys(@Param("panelId")Integer panelId);

    @Select("SELECT * FROM infrared_key WHERE panelId = #{panelId} AND keyId = #{KeyId}")
    InfraredPanel select_key(@Param("panelId")Integer panelId, @Param("panelId")Integer keyId);

    @Delete("DELETE FROM infrared_key WHERE panelId = #{panelId}")
    int delete_all_keys(@Param("panelId")Integer panelId);

    @Delete("DELETE FROM infrared_key WHERE panelId = #{panelId} AND keyId = #{keyId}")
    int delete_key(@Param("panelId")Integer panelId, @Param("panelId")Integer keyId);

    @Insert("INSERT INTO infrared_key(panelId, keyId, name, number) " +
            "VALUES(#{panelId},#{keyId}, #{name}, #{number}")
    int insert_key(InfraredKey key);

    @Update("UPDATE infrared_key set name = #{name} " +
            "WHERE keyId = #{keyId} AND panelId = #{panelId} AND number = #{number}")
    int update_key(InfraredKey key);
}
