package com.bupt.ZigbeeResolution.service;

import com.bupt.ZigbeeResolution.mapper.InfraredMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraredService {
    @Autowired
    InfraredMapper irMapper;

    public Integer findKey(String deviceId, Integer key){
        return irMapper.select_by_key_deviceId(deviceId, key);
    }

    public void addKey(String deviceId, Integer key, String name, Integer matchType) {
        irMapper.insert(deviceId, key, name, matchType);
    }

    public Integer get_maxkey_of_airCondition(String deviceId) {
        return irMapper.select_maxnum_of_airCondition(deviceId);
    }

    public Integer get_maxkey_of_non_airConditon(String deviceId) {
        return irMapper.select_maxnum_of_non_airCondition(deviceId);
    }

    public void deleteKey(String deviceId, Integer key) {
        irMapper.delete_a_key(deviceId, key);
    }

    public void deleteAllKey(String deviceId){
        irMapper.delete_all_key(deviceId);
    }

}
