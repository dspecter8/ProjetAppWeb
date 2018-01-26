package com.miage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miage.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	@Query("select m from Video m where m.codeMedia=:x")
	public List<Video> lstMedia(@Param("x")String idcli);
	
	
	@Query("select e from Video e where e.nom like :x")
	public List<Video> findByName(@Param("x")String mc);
	
	@Query("select e from Video e where e.etat=:x")
	public List<Video> lstMediaByEtat(@Param("x")int etat);
	
//	@Query("select e from Video e where e.etat=:x")
//	public Page<Video> lstMediaIndispo(@Param("x")int etat, Pageable pageable);
	
	@Query("select e from Video e where e.nom like :x")
	public Page<Video> findByName(@Param("x")String mc, Pageable page);
	
}
