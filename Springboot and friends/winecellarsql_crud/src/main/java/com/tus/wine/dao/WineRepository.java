package com.tus.wine.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tus.wine.model.Wine;

@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {

}
