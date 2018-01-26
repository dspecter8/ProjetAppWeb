package com.miage.metier;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miage.entities.Client;
import com.miage.entities.Emprunt;
import com.miage.entities.Media;

/**
 * @author HRezgui
 *
 */
public interface IEmpruntMetier {

	public List<Media >consulterMediaEmp(Client client);
	public List<Emprunt> consulterEmpByClient(Client client);
	public List<Emprunt> consulterEmpByDateOp(Date dateOp);
	
	public Emprunt consulterEmp(Long codeEmp);
	public List<Emprunt>listEmp();
	public void supprimerEmp(Long codeEmp);
	public void modifierEmp (Long codeEmp, Emprunt emp);
	
	
	
	
}
