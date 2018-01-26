package com.miage.metier.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.ClientRepository;
import com.miage.entities.Client;
import com.miage.metier.IClientMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class ClientMetierImpl  implements IClientMetier{
	
	@Autowired
	private ClientRepository clientRep;


	@Override
	public void ajouterClient(Client c) {
		  clientRep.save(c);
	}

	@Override
	public void activerClient(int etat, Long codeClient) {
		clientRep.activate(etat, codeClient);		
	}

	@Override
	public Client consulterClient(Long codeClient) {
		return clientRep.findOne(codeClient);
	}

	@Override
	public Page<Client> chercherClientParNom(String nom, Pageable page) {
		return clientRep.findByName(nom, page);
	}

	@Override
	public Page<Client> listClient(Pageable page) {
		return clientRep.findAll(page);
	}

	@Override
	public void modifierClient(Long code, Client c) {
		Client c2= clientRep.findOne(code);
		c2.setAdress(c.getAdress());
		c2.setCivilite(c.getCivilite());
		c2.setDebutAbonnement(c.getDebutAbonnement());
		c2.setEmail(c.getEmail());
		c2.setEmploye(c.getEmploye());
		c2.setEtatAbonnement(c.getEtatAbonnement());
		c2.setFinAbonnement(c.getFinAbonnement());
		c2.setDebutAbonnement(c.getDebutAbonnement());
		c2.setMotDePasse(c.getMotDePasse());
		c2.setNom(c.getNom());
		c2.setPrenom(c.getPrenom());
		c2.setTelephone(c.getTelephone());
		c2.setOperations(c.getOperations());
		
		clientRep.saveAndFlush(c2);
	}

	@Override
	public void effacerClient(Long code) {
		clientRep.delete(code);		
	}

}
