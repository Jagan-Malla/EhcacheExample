package com.EhcacheExample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EhcacheExample.model.Users;
@Repository
public interface EhcacheRepository extends JpaRepository<Users, Integer>{

	Users findByName(String name);

}
