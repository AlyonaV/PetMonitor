package com.springapp.mvc.persistence.dao;

import com.springapp.mvc.persistence.model.Role;

import java.util.List;

/**
 * Created by Alona on 21.05.2016.
 */

public interface RoleDao {
    Role getById(Integer id);

    List<Role> getAllRoles();

}
