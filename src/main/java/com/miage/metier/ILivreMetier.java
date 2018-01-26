package com.miage.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miage.entities.Livre;


/**
 * @author HRezgui
 *
 */
public interface ILivreMetier {
	
	public List<Livre> chercherLivreParNom(String nom);
	public List<Livre> listLivreDisop();
	public List<Livre> listLivreInDisop();
	
	public Livre consulterLivre(Long code);
	public List<Livre> listLivre();
	public void effacerLivre(Long code);
	public void modifierLivre (Long code, Livre video);

}
