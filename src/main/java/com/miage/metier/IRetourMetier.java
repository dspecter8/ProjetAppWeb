package com.miage.metier;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miage.entities.Client;
import com.miage.entities.Media;
import com.miage.entities.Retour;

/**
 * @author HRezgui
 *
 */

public interface IRetourMetier {
	
	public List<Media >consulterMediaRet(Client client);
	public List<Retour> consulterRetByClient(Client client);
	public List<Retour> consulterRetByDateOp(Date dateOp);
	
	public Retour consulterRet(Long codeRet);
	public List<Retour>listRet();
	public void supprimerRet(Long codeRet);
	public void modifierRet (Long codeRet, Retour ret);

}
