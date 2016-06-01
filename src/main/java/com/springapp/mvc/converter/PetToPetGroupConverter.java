package com.springapp.mvc.converter;

import com.springapp.mvc.persistence.model.PetGroup;
import com.springapp.mvc.service.PetGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PetToPetGroupConverter implements Converter<Object, PetGroup> {
    @Autowired
    PetGroupService petGroupService;

    @Override
    public PetGroup convert(Object o) {
        Integer id = Integer.parseInt((String) o);
        PetGroup group = petGroupService.getById(id);
        System.out.println("Group : " + group);
        return group;
    }
}
