package com.miage.metier.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.RetourRepository;
import com.miage.entities.Client;
import com.miage.entities.Media;
import com.miage.entities.Retour;
import com.miage.metier.IRetourMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class RetourMetierImp implements IRetourMetier {

	@Autowired
	private RetourRepository retourRep;
	
	@Override
	public List<Media> consulterMediaRet(Client client) {
		return retourRep.consulterMediaRet(client);
	}

	@Override
	public List<Retour> consulterRetByClient(Client client) {
		return retourRep.consulterRetByClient(client);
	}

	@Override
	public List<Retour> consulterRetByDateOp(Date dateOp) {
		return retourRep.consulterRetByDateOp(dateOp);
	}

	@Override
	public Retour consulterRet(Long codeRet) {
		return retourRep.findOne(codeRet);
	}

	@Override
	public List<Retour> listRet() {
		return retourRep.findAll();
	}

	@Override
	public void supprimerRet(Long codeRet) {
		retourRep.delete(codeRet);		
	}

	@Override
	public void modifierRet(Long codeRet, Retour ret) {
		

		 Retour ret2 = retourRep.findOne(codeRet);
		 ret2.setClient(ret.getClient());
		 ret2.setMedia(ret.getMedia());
		 
		 retourRep.saveAndFlush(ret2);
		
	}

}
