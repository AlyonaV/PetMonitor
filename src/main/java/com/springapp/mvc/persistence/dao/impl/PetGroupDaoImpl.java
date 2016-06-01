package com.springapp.mvc.persistence.dao.impl;

import com.springapp.mvc.persistence.dao.AbstractDao;
import com.springapp.mvc.persistence.dao.PetGroupDao;
import com.springapp.mvc.persistence.model.PetGroup;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("petGroup")
public class PetGroupDaoImpl extends AbstractDao<Integer, PetGroup> implements PetGroupDao {
    @Override
    public PetGroup getById(Integer id) {
        PetGroup petGroup = getByKey(id);
//        if(petGroup!=null){
//            Hibernate.initialize(petGroup.getPets());
//        }
        return petGroup;
    }

    @Override
    public PetGroup getByName(String name) {
        System.out.println("name: " + name);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (PetGroup) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PetGroup> getByUserId(Integer id) {
        System.out.println("user id: " + id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userId", id));
        return (List<PetGroup>) crit.list();
    }

    @Override
    public void save(PetGroup petGroup) {
        persist(petGroup);
    }

    @Override
    public void deleteById(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        PetGroup petGroup = (PetGroup)crit.uniqueResult();
        delete(petGroup);
    }
}
