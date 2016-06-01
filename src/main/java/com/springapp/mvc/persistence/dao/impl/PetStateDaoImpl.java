package com.springapp.mvc.persistence.dao.impl;

import com.springapp.mvc.persistence.dao.AbstractDao;
import com.springapp.mvc.persistence.dao.PetStateDao;
import com.springapp.mvc.persistence.model.PetState;
import org.springframework.stereotype.Repository;

/**
 * Created by Alona on 22.05.2016.
 */
@Repository("petState")
public class PetStateDaoImpl extends AbstractDao<Integer,PetState> implements PetStateDao{
    @Override
    public PetState getById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void save(PetState petState) {
        persist(petState);
    }

    @Override
    public void update(PetState petState) {
        persist(petState);
    }
}
