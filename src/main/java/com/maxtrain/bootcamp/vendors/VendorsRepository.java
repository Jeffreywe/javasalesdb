package com.maxtrain.bootcamp.vendors;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface VendorsRepository extends CrudRepository <Vendors, Integer> {

	Optional<Vendors> findByCode(String code);
}
