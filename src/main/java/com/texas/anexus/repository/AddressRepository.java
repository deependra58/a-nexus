package com.texas.anexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.anexus.model.Address;
import com.texas.anexus.util.Status;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

	Address findByIdAndStatusNot(Long id, Status deleted);

}
