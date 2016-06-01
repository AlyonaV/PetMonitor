package com.springapp.mvc.persistence.dao;

import com.springapp.mvc.persistence.model.Device;

import java.util.List;

/**
 * Created by Alona on 21.05.2016.
 */
public interface DeviceDao {

    Device findById(Integer id);

    Device findByImei(String imei);

    void save(Device device);

    void deleteById(Integer id);

    List<Device> getAllDevices();
}
