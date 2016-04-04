package com.uz.laboratory.statistical.dao;

import com.uz.laboratory.statistical.entity.Identifier;

import java.util.List;

public interface GenericDao<T extends Identifier> {

    T get(Long id);

    boolean isExists(Long id);

    void save(T obj);

    void delete(T obj);

    List<T> listAll();

    Class getEntityClass();
}
