package com.miage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.LivreRepository;
import com.miage.entities.Livre;
import com.miage.metier.ILivreMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class LivreMetierImp implements ILivreMetier {
	@Autowired
	private LivreRepository livreRep;

	@Override
	public List<Livre> chercherLivreParNom(String nom) {
		return livreRep.findByName(nom);
	}

	@Override
	public List<Livre> listLivreDisop() {
		return livreRep.lstMediaByEtat(1);
	}

	@Override
	public List<Livre> listLivreInDisop() {
		return livreRep.lstMediaByEtat(0);
	}

	@Override
	public Livre consulterLivre(Long code) {
		return livreRep.findOne(code);
	}

	@Override
	public List<Livre> listLivre() {
		return livreRep.findAll( );
	}

	@Override
	public void effacerLivre(Long code) {
		livreRep.delete(code);		
	}

	@Override
	public void modifierLivre(Long code, Livre v) {

		Livre v2 = livreRep.getOne(code);
		v2.setAuteur(v.getAuteur());
		v2.setAnneeDeSortie(v.getAnneeDeSortie());
		v2.setDateCreation(v.getDateCreation());
		v2.setEmploye(v.getEmploye());
		v2.setEtat(v.getEtat());
		v2.setNom(v.getNom());
		v2.setDescription(v.getDescription());
		v2.setPhoto(v.getPhoto());
		
		livreRep.saveAndFlush(v2);
	}

}
