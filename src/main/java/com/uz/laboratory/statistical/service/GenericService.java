package com.uz.laboratory.statistical.service;

import com.uz.laboratory.statistical.entity.Identifier;

import java.util.List;


public interface GenericService<T extends Identifier> {

    T get(long id);

    boolean isExists(long id);

    void save(T obj);

    void delete(T obj);

    void delete(Long id);

    List<T> listAll();
}
