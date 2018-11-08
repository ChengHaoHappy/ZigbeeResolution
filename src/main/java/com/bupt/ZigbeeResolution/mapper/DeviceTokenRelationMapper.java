package com.bupt.ZigbeeResolution.mapper;

import com.bupt.ZigbeeResolution.data.DeviceTokenRelation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeviceTokenRelationMapper {

    @Insert("INSERT INTO deviceTokenRelation(IEEE, endPoint, token, type, gatewayName,shortAddress, uuid) VALUES (#{IEEE}, #{endPoint}, #{token}, #{type}, #{gatewayName}, #{shortAddress}, #{uuid})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addARelation(DeviceTokenRelation deviceTokenRelation);

    @Select("SELECT * FROM deviceTokenRelation WHERE IEEE = #{IEEE} AND endPoint = #{endPoint}")
    DeviceTokenRelation getRelotionByIEEEAndEndPoint(@Param("IEEE") String IEEE, @Param("endPoint")Integer endPoint);

    @Select("SELECT * FROM deviceTokenRelation WHERE gatewayName = #{gatewayName} AND endPoint = #{endPoint}")
    DeviceTokenRelation getRelotionByGatewayNameAndEndPoint(@Param("gatewayName") String gatewayName, @Param("endPoint")Integer endPoint);

    @Select("SELECT * FROM deviceTokenRelation WHERE shortAddress = #{shortAddress} AND endPoint = #{endPoint}")
    DeviceTokenRelation getRelotionBySAAndEndPoint(@Param("shortAddress") String shortAddress, @Param("endPoint")Integer endPoint);

    @Select("SELECT * FROM deviceTokenRelation WHERE gatewayName = #{gatewayName} AND type = 'Gateway'")
    DeviceTokenRelation getGateway(@Param("gatewayName") String gatewayName);

    @Select("SELECT auto_increment FROM information_schema.tables where table_schema='BUPT_IOT' and table_name='deviceTokenRelation'")
    Integer getdeviceNumber();

    @Select("SELECT * FROM deviceTokenRelation WHERE uuid = #{uuid}")
    DeviceTokenRelation getRelationByUuid(@Param("uuid") String uuid);

    @Update("UPDATE deviceTokenRelation SET shortAddress = #{shortAddress} WHERE IEEE = #{IEEE}")
    Integer updateShortAddress(@Param("shortAddress")String shortAddress, @Param("IEEE")String IEEE);

    @Update("UPDATE deviceTokenRelation SET gatewayName = #{gatewayName} WHERE IEEE = #{IEEE}")
    Integer updateGatewayName(@Param("gatewayName")String gatewayName,@Param("IEEE") String IEEE);

    @Delete("DELETE FROM deviceTokenRelation WHERE IEEE = #{IEEE}")
    Integer deleteDeviceByIEEE(@Param("IEEE")String IEEE);
}