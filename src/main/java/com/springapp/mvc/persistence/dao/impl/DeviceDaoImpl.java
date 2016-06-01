package com.springapp.mvc.persistence.dao.impl;

import com.springapp.mvc.persistence.dao.AbstractDao;
import com.springapp.mvc.persistence.dao.DeviceDao;
import com.springapp.mvc.persistence.model.Device;
import com.springapp.mvc.persistence.model.Pet;
import com.springapp.mvc.persistence.model.PetGroup;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deviceDao")
public class DeviceDaoImpl extends AbstractDao<Integer, Device> implements DeviceDao {
    @Override
    public Device findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public Device findByImei(String imei) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("imei", imei));
        return (Device) crit.uniqueResult();
    }

    @Override
    public void save(Device device) {
        persist(device);
    }

    @Override
    public void deleteById(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Device device = (Device) crit.uniqueResult();
        delete(device);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Device> getAllDevices() {
        Criteria crit = createEntityCriteria();
        List<Device> devices = (List<Device>) crit.list();
//        for(Device device:devices){
//            Hibernate.initialize(device.getPet());
//            Pet pet = device.getPet();
//            Hibernate.initialize(pet.getPetGroups());
//        }
        return (List<Device>) crit.list();
    }
}
