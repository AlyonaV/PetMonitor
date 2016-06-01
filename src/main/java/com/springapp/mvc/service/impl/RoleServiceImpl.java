package com.springapp.mvc.service.impl;

import com.springapp.mvc.persistence.dao.RoleDao;
import com.springapp.mvc.persistence.model.Role;
import com.springapp.mvc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao dao;

    @Override
    public Role getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }
}
