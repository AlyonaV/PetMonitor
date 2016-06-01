package com.springapp.mvc.persistence.dao;

import com.springapp.mvc.persistence.model.PetGroup;

import java.util.List;

/**
 * Created by Alona on 21.05.2016.
 */
public interface PetGroupDao {
    PetGroup getById(Integer id);

    PetGroup getByName(String name);

    List<PetGroup> getByUserId(Integer id);

    void save(PetGroup petGroup);

    void deleteById(Integer id);

}
