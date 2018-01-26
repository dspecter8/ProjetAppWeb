package com.miage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miage.entities.Client;
import com.miage.entities.Emprunt;
import com.miage.entities.Media;

/**
 * @author HRezgui
 *
 */
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	@Query("select e.media  from Emprunt e   where e.client=:x ")
	List<Media> consulterMediaEmp(@Param("x")Client client);
	
	@Query("select e  from Emprunt e   where e.client=:x ")
	List<Emprunt> consulterEmpByClient(Client client);
	
	@Query("select e  from Emprunt e   where e.dateOperation=:x ")
	List<Emprunt> consulterEmpByDateOp(Date dateOp);

}
