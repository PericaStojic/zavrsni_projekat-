package com.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
}
