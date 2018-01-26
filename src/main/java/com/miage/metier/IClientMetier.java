package com.miage.metier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miage.entities.Client;

/**
 * @author Specter
 *
 */
public interface IClientMetier {
	
	public void ajouterClient(Client c);
	public void activerClient(int etat, Long codeClient);
	public Client consulterClient(Long codeClient);
	//public void consulterEmprunt(Long codeClient);
	//public Media rechercherMediaByTags(String codeclient);
	//public Page<Media> lstMedia(String codeMedia, int page, int siez);
	
	public Page<Client> chercherClientParNom(String nom, Pageable page);
	public Page<Client> listClient(Pageable page);
	public void modifierClient(Long code, Client client);
	public void effacerClient(Long code);
	
}
