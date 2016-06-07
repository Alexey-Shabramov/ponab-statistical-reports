package com.uz.laboratory.statistical.dao.daoImpl;

import com.uz.laboratory.statistical.dao.AbstractDao;
import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Table;
import java.util.List;


public abstract class GenericDaoImpl<T extends Identifier> extends AbstractDao implements GenericDao {
    private final static StringBuilder stringBuilder = new StringBuilder();

    @Override
    public abstract Class getEntityClass();

    @Override
    public T get(Long id) {
        return (T) getSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("id", id))
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public boolean isExists(Long id) {
        return get(id) != null;
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

    public void delete(Long id) {
        try {
            getSession()
                    .createSQLQuery(
                            stringBuilder.append("delete from ")
                                    .append(((Table) getEntityClass().getAnnotation(Table.class)).name())
                                    .append(" where id = ")
                                    .append(id)
                                    .toString())
                    .executeUpdate();
        } finally {
            stringBuilder.setLength(0);
        }

    }

    @Override
    public List<T> listAll() {
        return (List<T>) getSession().createCriteria(getEntityClass()).list();
    }
}
