package com.tus.cars.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tus.cars.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Find cars within a manufacturer year range
    @Query("SELECT c FROM Car c WHERE c.manufacturerYear BETWEEN :minYear AND :maxYear")
    List<Car> findByManufacturerYearBetween(@Param("minYear") int minYear, @Param("maxYear") int maxYear);
	

}
