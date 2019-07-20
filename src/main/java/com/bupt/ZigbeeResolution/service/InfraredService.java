package com.bupt.ZigbeeResolution.service;

import com.bupt.ZigbeeResolution.data.CustomerLearn;
import com.bupt.ZigbeeResolution.mapper.InfraredMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfraredService {

    @Autowired
    InfraredMapper irMapper;

    public Integer findKey(String deviceId, Integer key) {
        return irMapper.select_by_key_deviceId(deviceId, key);
    }

    public void addKey(String deviceId, Integer key, String name, Integer matchType, Integer customerId, Integer buttonId,
                       Integer panelId, Integer status) {
        irMapper.insert(deviceId, key, name, matchType, customerId, buttonId, panelId, status);
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

    public void deleteAllKey(String deviceId) {
        irMapper.delete_all_key(deviceId);
    }

    public void updateState(String deviceId, Integer key) {
        irMapper.updateStateByResult(deviceId, key);
    }

    public List<CustomerLearn> getCustomerLearns(String deviceId, Integer customerId) {
        List<CustomerLearn> customerLearns = irMapper.selectCustomerLearn(deviceId, customerId);
        return customerLearns;
    }

    public List<String> getKeyNames(String deviceId, Integer customerId, Integer panelId) {
        List<String> keyNames = irMapper.selectKeyNames(deviceId, customerId, panelId);
        return keyNames;
    }
}
