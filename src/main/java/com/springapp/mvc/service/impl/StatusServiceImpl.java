package com.springapp.mvc.service.impl;

import com.springapp.mvc.persistence.dao.StatusDao;
import com.springapp.mvc.persistence.model.Status;
import com.springapp.mvc.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService{
    @Autowired
    private StatusDao dao;

    @Override
    public Status getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public void save(Status status) {
        dao.save(status);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Status> getAllStatuses() {
        return dao.getAllStatuses();
    }
}
