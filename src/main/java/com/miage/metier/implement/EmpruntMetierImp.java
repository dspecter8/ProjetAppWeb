package com.miage.metier.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.EmpruntRepository;
import com.miage.entities.Client;
import com.miage.entities.Emprunt;
import com.miage.entities.Media;
import com.miage.metier.IEmpruntMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class EmpruntMetierImp implements IEmpruntMetier {

	@Autowired
	private EmpruntRepository empruntRep;
	
	@Override
	public List<Media> consulterMediaEmp(Client client) {
		
		return empruntRep.consulterMediaEmp(client);
	}

	@Override
	public List<Emprunt> consulterEmpByClient(Client client) {
		
		return empruntRep.consulterEmpByClient(client);
	}

	@Override
	public List<Emprunt> consulterEmpByDateOp(Date dateOp) {
		 
		return empruntRep.consulterEmpByDateOp(dateOp);
	}

	@Override
	public Emprunt consulterEmp(Long codeEmp) {
		 
		return empruntRep.findOne(codeEmp);
	}

	@Override
	public List<Emprunt> listEmp() {
		 
		return empruntRep.findAll();
	}

	@Override
	public void supprimerEmp(Long codeEmp) {
		empruntRep.delete(codeEmp);
		
	}

	@Override
	public void modifierEmp(Long codeEmp, Emprunt emp) {
		
		 Emprunt emp2 = empruntRep.findOne(codeEmp);
		 emp2.setClient(emp.getClient());
		 emp2.setDateLimRetour(emp.getDateLimRetour());
		 emp2.setMedia(emp.getMedia());
		 
		 empruntRep.saveAndFlush(emp2);
		
	}

}
