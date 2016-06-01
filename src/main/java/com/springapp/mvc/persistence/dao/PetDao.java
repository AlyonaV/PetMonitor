package com.springapp.mvc.persistence.dao;

import com.springapp.mvc.persistence.model.Pet;

import java.util.List;

/**
 * Created by Alona on 21.05.2016.
 */
public interface PetDao {

    Pet getById (Integer id);

    void save(Pet pet);

    void deleteById(Integer id);

    List<Pet> getAllPets();
}
