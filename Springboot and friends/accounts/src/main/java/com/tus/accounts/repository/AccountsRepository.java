package com.tus.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tus.accounts.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{
	Optional<Accounts> findCustomerById(long customerId);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(long customerId);
}
