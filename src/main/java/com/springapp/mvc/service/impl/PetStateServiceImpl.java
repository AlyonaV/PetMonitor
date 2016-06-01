package com.springapp.mvc.service.impl;

import com.springapp.mvc.persistence.dao.PetStateDao;
import com.springapp.mvc.persistence.model.PetState;
import com.springapp.mvc.service.PetStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("petStateService")
@Transactional
public class PetStateServiceImpl implements PetStateService{
    @Autowired
    PetStateDao dao;

    @Override
    public PetState getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public void save(PetState petState) {
        dao.save(petState);
    }

    @Override
    public void update(PetState petState) {
        dao.update(petState);
    }
}
