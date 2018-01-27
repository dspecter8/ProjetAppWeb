package com.miage.metier;

import java.util.Date;
import java.util.List;

import com.miage.entities.Client;
import com.miage.entities.Media;
import com.miage.entities.Reservation;

/**
 * @author HRezgui
 *
 */
public interface IReservationMetier {
	
	public List<Media >consulterMediaRes(Client client);
	public List<Reservation> consulterResByClient(Client client);
	public List<Reservation> consulterResByDateOp(Date dateOp);
	
	public Reservation consulterRes(Long codeRet);
	public List<Reservation>listRes();
	public void supprimerRes(Long codeRet);
	public void modifierRes ( Reservation ret);
	public void ajouterRes(Reservation res);


}
