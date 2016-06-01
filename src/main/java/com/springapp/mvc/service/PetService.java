package com.springapp.mvc.service;

import com.springapp.mvc.persistence.model.Pet;

import java.util.List;

/**
 * Created by Alona on 22.05.2016.
 */
public interface PetService {

    Pet getById (Integer id);

    void save(Pet pet);

    void deleteById(Integer id);

    List<Pet> getAllPets();

    void updatePet(Pet pet);
}
