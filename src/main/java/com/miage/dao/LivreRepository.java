package com.miage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miage.entities.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {
	@Query("select m from Livre m where m.codeMedia=:x")
	public List<Livre> lstMedia(@Param("x")String idcli);
	
	
	@Query("select e from Livre e where e.nom like :x")
	public List<Livre> findByName(@Param("x")String mc);
	
	@Query("select e from Livre e where e.etat=:x")
	public List<Livre> lstMediaByEtat(@Param("x")int etat);
	
//	@Query("select e from Livre e where e.etat=:x")
//	public Page<Livre> lstMediaIndispo(@Param("x")int etat, Pageable pageable);
	@Query("select e from Livre e where e.nom like :x")
	public Page<Livre> findByName(@Param("x")String mc, Pageable page);
//	
}
