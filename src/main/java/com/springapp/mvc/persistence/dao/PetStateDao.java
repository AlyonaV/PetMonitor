package com.springapp.mvc.persistence.dao;

import com.springapp.mvc.persistence.model.PetState;

/**
 * Created by Alona on 21.05.2016.
 */
public interface PetStateDao {
    PetState getById(Integer id);

    void save(PetState petState);

    void update(PetState petState);

}
