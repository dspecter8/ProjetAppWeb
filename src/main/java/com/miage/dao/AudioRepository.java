package com.miage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miage.entities.Audio;

public interface AudioRepository extends JpaRepository<Audio, Long> {
	@Query("select m from Audio m where m.codeMedia=:x")
	public List<Audio> lstMedia(@Param("x")String idcli);
	
	
	@Query("select e from Audio e where e.nom like :x")
	public List<Audio> findByName(@Param("x")String mc);
	
	@Query("select e from Audio e where e.etat=:x")
	public List<Audio> lstMediaByEtat(@Param("x")int etat);
	
//	@Query("select e from Audio e where e.etat=:x")
//	public Page<Audio> lstMediaIndispo(@Param("x")int etat, Pageable pageable);
	
	@Query("select e from Audio e where e.nom like :x")
	public Page<Audio> findByName(@Param("x")String mc,Pageable page);
	
}
