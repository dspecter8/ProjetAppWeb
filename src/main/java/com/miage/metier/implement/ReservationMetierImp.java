package com.miage.metier.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.ReservationRepository;
import com.miage.dao.RetourRepository;
import com.miage.entities.Client;
import com.miage.entities.Media;
import com.miage.entities.Reservation;
import com.miage.metier.IReservationMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class ReservationMetierImp implements IReservationMetier{

	@Autowired
	private ReservationRepository reservationRep;
	@Override
	public List<Media> consulterMediaRes(Client client) {
		// TODO Auto-generated method stub
		return reservationRep.consulterMediaRes(client);
	}

	@Override
	public List<Reservation> consulterResByClient(Client client) {
		// TODO Auto-generated method stub
		return reservationRep.consulterResByClient(client);
	}

	@Override
	public List<Reservation> consulterResByDateOp(Date dateOp) {
		// TODO Auto-generated method stub
		return reservationRep.consulterResByDateOp(dateOp);
	}

	@Override
	public Reservation consulterRes(Long codeRes) {
		// TODO Auto-generated method stub
		return reservationRep.findOne(codeRes);
	}

	@Override
	public List<Reservation> listRes() {
		// TODO Auto-generated method stub
		return reservationRep.findAll();
	}

	@Override
	public void supprimerRes(Long codeRes) {
		// TODO Auto-generated method stub
		reservationRep.delete(codeRes);
	}

	@Override
	public void modifierRes( Reservation res) {
		reservationRep.saveAndFlush(res)
;		
	}

	@Override
	public void ajouterRes(Reservation res) {
		reservationRep.save(res);
		
	}

}
