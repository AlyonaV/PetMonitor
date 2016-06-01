package com.springapp.mvc.persistence.dao.impl;

import com.springapp.mvc.persistence.dao.AbstractDao;
import com.springapp.mvc.persistence.dao.ActivityDao;
import com.springapp.mvc.persistence.model.Activity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityDao")
public class ActivityDaoImpl extends AbstractDao<Integer, Activity> implements ActivityDao {
    @Override
    public Activity getById(Integer id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Activity> getByDeviceId(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("deviceId", id));
        return (List<Activity>) crit.list();
    }

    @Override
    public void save(Activity activity) {
        persist(activity);
    }

    @Override
    public void deleteById(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Activity activity = (Activity) crit.uniqueResult();
        delete(activity);
    }
}
