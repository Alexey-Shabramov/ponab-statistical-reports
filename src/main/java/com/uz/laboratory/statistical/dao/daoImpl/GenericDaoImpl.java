package com.uz.laboratory.statistical.dao.daoImpl;

import com.uz.laboratory.statistical.dao.AbstractDao;
import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public abstract class GenericDaoImpl<T extends Identifier> extends AbstractDao implements GenericDao {

    @Override
    public T get(Long id) {
        return (T) getSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("id", id))
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public boolean isExists(Long id) {
        return false;
    }


    @Override
    public void save(Identifier entity) {
        System.out.println("entity ID" + entity.getId());
        if (entity.isNew()) {
            System.out.println("Adding new entity.");
            getSession().save(entity);
        } else {
            System.out.println("Merging entity.");
            Object merged = getSession().merge(entity);
        }
    }

    @Override
    public void delete(Identifier entity) {
        getSession().delete(entity);
    }

    @Override
    public List<T> listAll() {
        return (List<T>) getSession().createCriteria(getEntityClass()).list();
    }
}
