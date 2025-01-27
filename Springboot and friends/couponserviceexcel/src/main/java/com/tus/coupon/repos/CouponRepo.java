package com.tus.coupon.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tus.coupon.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Coupon")
    void deleteAllEntities();

}
