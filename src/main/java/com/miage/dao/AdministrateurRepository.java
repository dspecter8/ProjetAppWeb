package com.miage.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miage.entities.Administrateur;
import com.miage.entities.Client;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
	@Query("select e from Administrateur e where e.email =:x")
	public Administrateur findByEmail(@Param("x")String mc);
}
