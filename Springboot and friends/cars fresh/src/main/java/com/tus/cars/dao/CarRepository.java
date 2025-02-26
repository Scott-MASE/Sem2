package com.tus.cars.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tus.cars.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	

}
