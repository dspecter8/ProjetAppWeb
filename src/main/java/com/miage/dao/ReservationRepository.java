package com.miage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miage.entities.Client;
import com.miage.entities.Media;
import com.miage.entities.Reservation;
import com.miage.entities.Retour;
/**
 * @author HRezgui
 *
 */

public interface ReservationRepository  extends JpaRepository<Reservation, Long>{


	@Query("select e.media  from Reservation e   where e.client=:x ")
	List<Media> consulterMediaRes(@Param("x")Client client);

	@Query("select e  from Reservation e   where e.client=:x ")
	List<Reservation> consulterResByClient(@Param("x")Client client);
	
	@Query("select e  from Reservation e   where e.dateOperation=:x ")
	List<Reservation> consulterResByDateOp(@Param("x")Date dateOp);

}
