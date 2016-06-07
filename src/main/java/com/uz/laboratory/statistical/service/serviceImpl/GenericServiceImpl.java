package com.uz.laboratory.statistical.service.serviceImpl;

import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class GenericServiceImpl<T extends Identifier, D extends GenericDao> implements GenericService {

    protected D dao;

    public void setDao(D dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public T get(long id) {
        return (T) dao.get(id);
    }

    @Override
    @Transactional
    public boolean isExists(long id) {
        return dao.isExists(id);
    }

    @Override
    @Transactional
    public void save(Identifier obj) {
        dao.save(obj);
    }

    @Override
    @Transactional
    public void delete(Identifier obj) {
        dao.delete(obj);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        delete(get(id));
    }

    @Override
    @Transactional
    public List<T> listAll() {
        return dao.listAll();
    }
}
