package com.springapp.mvc.service;

import com.springapp.mvc.persistence.model.PetState;

/**
 * Created by Alona on 22.05.2016.
 */
public interface PetStateService {
    PetState getById(Integer id);

    void save(PetState petState);

    void update(PetState petState);

}
